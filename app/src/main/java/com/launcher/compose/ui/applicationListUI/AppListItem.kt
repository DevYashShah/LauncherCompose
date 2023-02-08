package com.launcher.compose.ui.applicationListUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.launcher.compose.data.models.ApplicationListData
import com.launcher.compose.ui.theme.ColorTextBlack
import com.launcher.compose.ui.theme.ColorTextLight

@Composable
fun AppListItem(dataItems: ApplicationListData, openApp: (packageName: String?) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                openApp(dataItems.packageName)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        dataItems.icon?.toBitmap()?.asImageBitmap()?.let {
            Image(
                bitmap = it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
        }

        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = dataItems.appName ?: "N/A",
                color = ColorTextBlack,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 10.dp),
            )

            Text(
                text = dataItems.packageName ?: "N/A",
                color = ColorTextLight,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp),
            )
        }
    }
}