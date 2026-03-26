package cuevas.jorge.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import cuevas.jorge.login.ui.screens.HomeScreen
import cuevas.jorge.login.ui.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = PreferenceManager(this)

        enableEdgeToEdge()
        setContent {

            var screenState by remember { mutableStateOf(if (prefs.isLoggedIn()) "HOME" else "LOGIN") }

            if(screenState == "LOGIN") {
                LoginScreen(onLoginClick = {
                    prefs.saveLoginStatus(true)
                    screenState = "HOME"
                })
            } else {
                HomeScreen(onLogoutClick = {
                    prefs.logout()
                    screenState = "LOGIN"
                })
            }

        }
    }
}
