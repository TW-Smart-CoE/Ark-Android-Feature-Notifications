package com.thoughtworks.ark.feature.notifications.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.thoughtworks.ark.feature.notifications.api.NotificationsSchemes
import com.thoughtworks.ark.router.annotation.Scheme
import com.thoughtworks.ark.ui.theme.Theme

@Scheme(NotificationsSchemes.NOTIFICATIONS)
class NotificationsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext()).apply {
        setContent {
            Theme {
                NotificationsScreen()
            }
        }
    }
}