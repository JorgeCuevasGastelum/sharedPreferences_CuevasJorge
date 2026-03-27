package cuevas.jorge.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import cuevas.jorge.login.data.models.CarritoManager
import cuevas.jorge.login.ui.screens.HomeScreen
import cuevas.jorge.login.ui.screens.LoginScreen
import cuevas.jorge.login.ui.screens.PantallaCarrito
import cuevas.jorge.login.ui.screens.PantallaDetalle
import cuevas.jorge.login.ui.screens.PantallaProductos

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = PreferenceManager(this)

        setContent {

            val context = LocalContext.current

            LaunchedEffect (Unit) {
                CarritoManager.cargar(context)
            }

            var screenState by remember {
                mutableStateOf(if (prefs.isLoggedIn()) "PRODUCTOS" else "LOGIN")
            }

            var productoSeleccionadoId by remember { mutableStateOf(0) }

            when (screenState) {

                "LOGIN" -> LoginScreen(
                    onLoginClick = {
                        prefs.saveLoginStatus(true)
                        screenState = "PRODUCTOS"
                    }
                )

                "PRODUCTOS" -> PantallaProductos(
                    onCarritoClick = {
                        screenState = "CARRITO"
                    },
                    onDetalle = { id ->
                        productoSeleccionadoId = id
                        screenState = "DETALLE"
                    },
                    onLogoutClick = {
                        prefs.logout()
                        screenState = "LOGIN"
                    }
                )

                "DETALLE" -> PantallaDetalle(
                    id = productoSeleccionadoId,
                    onBack = { screenState = "PRODUCTOS" }
                )

                "CARRITO" -> PantallaCarrito(
                    onBack = { screenState = "PRODUCTOS" }
                )
            }
        }
    }
}