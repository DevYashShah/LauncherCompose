package com.launcher.compose.ui


import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.provider.Settings
import androidx.activity.compose.setContent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.launcher.compose.ui.applicationListUI.AppList
import com.launcher.compose.ui.theme.LauncherTheme
import com.launcher.compose.utils.loadApps

class MainActivity : AppCompatActivity() {

    private val Context.isMyLauncherDefault: Boolean
        get() = ArrayList<ComponentName>().apply {
            packageManager.getPreferredActivities(
                arrayListOf(IntentFilter(Intent.ACTION_MAIN).apply { addCategory(Intent.CATEGORY_HOME) }),
                this,
                packageName
            )
        }.isNotEmpty()

    override fun onResume() {
        super.onResume()
        if (isMyLauncherDefault) {
            setContent {
                LauncherTheme {
                    AppList(modifier = Modifier.fillMaxSize(), loadApps(packageManager)) {
                        it?.let {
                            packageManager.getLaunchIntentForPackage(it)?.let { startActivity(it) }
                        }
                    }
                }
            }
        } else {
            with(AlertDialog.Builder(this))
            {
                setTitle("Set As Launcher??")
                setMessage("We would like you to set this Compose Launcher app, as the Home Launcher to unlock it's full potential !!")
                setPositiveButton("Yes, Please.") { _, _ -> startActivity(Intent(Settings.ACTION_HOME_SETTINGS)) }
                setNegativeButton("Not Now.") { _, _ -> finishAffinity() }
                show()
            }
        }
    }
}

