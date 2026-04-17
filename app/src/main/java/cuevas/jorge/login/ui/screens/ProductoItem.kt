package cuevas.jorge.login.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cuevas.jorge.login.DataStoreManager
import cuevas.jorge.login.data.models.CarritoManager
import cuevas.jorge.login.data.models.Producto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ProductoItem(
    producto: Producto,
    onDetalle: (Int) -> Unit,
    scope: CoroutineScope,
    prefs: DataStoreManager
) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row (modifier = Modifier.padding(8.dp)) {

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
                        scope.launch {
                            prefs.guardarCarrito(CarritoManager.carrito)
                        }
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