package cuevas.jorge.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import cuevas.jorge.login.data.models.CarritoManager
import cuevas.jorge.login.ui.screens.HomeScreen
import cuevas.jorge.login.ui.screens.LoginScreen
import cuevas.jorge.login.ui.screens.PantallaCarrito
import cuevas.jorge.login.ui.screens.PantallaDetalle
import cuevas.jorge.login.ui.screens.PantallaProductos
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = DataStoreManager(this)

        setContent {
            val context = LocalContext.current
            val scope = rememberCoroutineScope()

            val isLoggedIn by prefs.isLoggedIn.collectAsState(initial = null)
            val carritoData by prefs.carritoFlow.collectAsState(initial = emptyList())

            LaunchedEffect(carritoData) {
                CarritoManager.carrito.clear()
                CarritoManager.carrito.addAll(carritoData)
            }

            if (isLoggedIn == null) return@setContent

            var screenState by remember {
                mutableStateOf(if (isLoggedIn == true) "PRODUCTOS" else "LOGIN")
            }

            LaunchedEffect(isLoggedIn) {
                screenState = if (isLoggedIn == true) "PRODUCTOS" else "LOGIN"
            }

            var productoSeleccionadoId by remember { mutableIntStateOf(0) }

            when (screenState) {
                "LOGIN" -> LoginScreen(
                    onLoginClick = {
                        scope.launch { prefs.saveLoginStatus(true) }
                    }
                )
                "PRODUCTOS" -> PantallaProductos(
                    onCarritoClick = { screenState = "CARRITO" },
                    onDetalle = { id ->
                        productoSeleccionadoId = id
                        screenState = "DETALLE"
                    },
                    onLogoutClick = {
                        scope.launch {
                            prefs.clearAll()
                            CarritoManager.carrito.clear()
                        }
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
