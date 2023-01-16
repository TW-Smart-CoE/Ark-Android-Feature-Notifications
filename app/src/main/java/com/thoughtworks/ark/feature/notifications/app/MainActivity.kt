package com.thoughtworks.ark.feature.notifications.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.ark.feature.notifications.api.NotificationsSchemes
import com.thoughtworks.ark.router.Router
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Router.scheme(NotificationsSchemes.NOTIFICATIONS).route(this)
    }
}