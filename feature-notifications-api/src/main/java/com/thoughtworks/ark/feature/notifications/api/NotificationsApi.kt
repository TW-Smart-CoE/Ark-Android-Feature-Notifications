package com.thoughtworks.ark.feature.notifications.api

import androidx.fragment.app.Fragment
import com.thoughtworks.ark.router.annotation.Service

@Service
interface NotificationsApi {
    fun getNotificationsFragment(): Fragment
}