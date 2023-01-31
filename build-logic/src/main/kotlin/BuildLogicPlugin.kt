import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create

class BuildLogicPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.subprojects {
            configMaven()
        }
    }

    private fun Project.readConfig(name: String, defaultValue: String = ""): String {
        return project.properties[name] as String? ?: System.getenv(name) ?: defaultValue
    }

    private fun Project.configMaven() {
        afterEvaluate {
            plugins.withId("com.android.library") {
                apply(plugin = "maven-publish")
                configure<PublishingExtension> {
                    repositories {
                        maven {
                            url = uri(readConfig("MAVEN_REPO"))
                            isAllowInsecureProtocol = true
                            credentials {
                                username = readConfig("MAVEN_USER")
                                password = readConfig(("MAVEN_PWD"))
                            }
                        }
                    }
                    publications {
                        create<MavenPublication>("lib") {
                            afterEvaluate {
                                val buildEnv = readConfig("buildEnv")

                                val componentName = buildEnv.ifEmpty { "dev" }

                                from(components.getByName("${componentName}Release"))
                                groupId = "com.thoughtworks.ark"
                                version = "1.0.0"

                                val newVersion = readConfig("publishVersion")
                                if (newVersion.isNotEmpty()) {
                                    version = newVersion
                                }

                                if (buildEnv.isNotEmpty()) {
                                    artifactId += "-$buildEnv"
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
