package com.launcher.compose.data.models

import android.graphics.drawable.Drawable

data class ApplicationListData(
    val appName: String? = null,
    var packageName: String? = null,
    var icon: Drawable? = null,
    var isSystemApp: Boolean = false
)