package cuevas.jorge.login.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cuevas.jorge.login.data.models.CarritoManager

@Composable
fun PantallaCarrito(onBack: () -> Unit) {
    val carrito = CarritoManager.carrito

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp)
    ) {

        Button(onClick = { onBack() }) {
            Text("Volver")
        }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(carrito) { producto ->

                Card(modifier = Modifier.padding(vertical = 8.dp)) {
                    Row(modifier = Modifier.padding(8.dp)) {

                        Image(
                            painter = painterResource(producto.imagen),
                            contentDescription = producto.nombre,
                            modifier = Modifier.size(80.dp)
                        )

                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text(producto.nombre)
                            Text("$${producto.precio}")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Total: $${CarritoManager.total()}")
        Text("Cantidad: ${CarritoManager.cantidad()}")
    }
}