package com.launcher.compose.ui


import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.launcher.compose.ui.applicationListUI.AppList
import com.launcher.compose.ui.theme.LauncherTheme
import com.launcher.compose.utils.isMyLauncherDefault
import com.launcher.compose.utils.loadApps

class MainActivity : AppCompatActivity() {

    private val homeAppResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        initApp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initApp()
    }

    private fun initApp() {
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
                setPositiveButton("Yes, Please.") { _, _ -> homeAppResultLauncher.launch(Intent(Settings.ACTION_HOME_SETTINGS)) }
                setNegativeButton("Not Now.") { _, _ -> finishAffinity() }
                show()
            }
        }
    }
}

