package com.launcher.compose.ui.applicationListUI

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.launcher.compose.data.models.ApplicationListData

@Composable
fun AppList(modifier: Modifier = Modifier, appListDataItems: List<ApplicationListData>, openApp:(packageName: String?) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(items = appListDataItems) { data -> AppListItem(dataItems = data,openApp) }
    }
}