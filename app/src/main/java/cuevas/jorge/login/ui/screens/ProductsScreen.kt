package cuevas.jorge.login.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cuevas.jorge.login.DataStoreManager
import cuevas.jorge.login.data.models.CarritoManager
import cuevas.jorge.login.data.models.Producto
import cuevas.jorge.login.data.models.ProductosData
import kotlinx.coroutines.launch

@Composable
fun PantallaProductos(
    onCarritoClick: () -> Unit,
    onDetalle: (Int) -> Unit,
    onLogoutClick: () -> Unit
) {
    var query by remember { mutableStateOf("") }
    val productos = ProductosData.filtrarProductos(query)

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val prefs = remember { DataStoreManager(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Buscar...") }
            )

            Button(onClick = onCarritoClick) {
                Text("🛒")
            }

            Button(onClick = {
                // limpieza en una corrutina
                scope.launch {
                    CarritoManager.carrito.clear() // Limpia UI
                    prefs.clearAll()               // Limpia DataStore
                    onLogoutClick()                // Navega al Login
                }
            }) {
                Text("Salir")
            }
        }

        LazyColumn {
            items(productos) { producto ->
                // Pasamos el scope y prefs a cada item
                ProductoItem(producto, onDetalle, scope, prefs)
            }
        }
    }
}



