package com.launcher.compose.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import com.launcher.compose.data.models.ApplicationListData


fun loadApps(packageManager: PackageManager): List<ApplicationListData> {
    val loadList = mutableListOf<ApplicationListData>()

    val allApps = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        packageManager.queryIntentActivities(Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER), PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_ALL.toLong()))
    } else {
        packageManager.queryIntentActivities(Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER), PackageManager.GET_META_DATA)
    }

    for (ri in allApps) {
        val app = ApplicationListData(appName = ri.loadLabel(packageManager).toString(), packageName = ri.activityInfo.packageName, icon = ri.activityInfo.loadIcon(packageManager))
        loadList.add(app)
    }
    loadList.sortBy { it.appName.toString() }
    return loadList
}