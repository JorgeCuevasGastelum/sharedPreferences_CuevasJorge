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
import cuevas.jorge.login.data.models.CarritoManager
import cuevas.jorge.login.data.models.Producto
import cuevas.jorge.login.data.models.ProductosData

@Composable
fun PantallaProductos(
    onCarritoClick: () -> Unit,
    onDetalle: (Int) -> Unit,
    onLogoutClick: () -> Unit
) {
    var query by remember { mutableStateOf("") }
    val productos = ProductosData.filtrarProductos(query)

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    )  {

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
                CarritoManager.limpiar(context)
                onLogoutClick()
            }) {
                Text("Salir")
            }
        }

        LazyColumn {
            items(productos) { producto ->
                ProductoItem(producto, onDetalle)
            }
        }
    }
}
@Composable
fun ProductoItem(producto: Producto, onDetalle: (Int) -> Unit) {

    val context = LocalContext.current

    Card(modifier = Modifier.padding(8.dp)) {
        Row(modifier = Modifier.padding(8.dp)) {

            Image(
                painter = painterResource(producto.imagen),
                contentDescription = producto.nombre,
                modifier = Modifier.size(80.dp)
            )

            Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                Text(producto.nombre)
                Text("$${producto.precio}")

                Row {
                    Button(onClick = {
                        CarritoManager.agregar(producto)
                        CarritoManager.guardar(context)
                    }) {
                        Text("Agregar")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(onClick = { onDetalle(producto.id) }) {
                        Text("Ver")
                    }
                }
            }
        }
    }
}



