package cuevas.jorge.login.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cuevas.jorge.login.data.models.CarritoManager
import cuevas.jorge.login.data.models.ProductosData

@Composable
fun PantallaDetalle(id: Int, onBack: () -> Unit) {
    val producto = ProductosData.obtenerProductoPorId(id)
    val context = LocalContext.current


    producto?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(16.dp)
        ) {

            Button(onClick = { onBack() }) {
                Text("Volver")
            }

            Image(
                painter = painterResource(it.imagen),
                contentDescription = it.nombre,
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )

            Text(it.nombre, style = MaterialTheme.typography.titleLarge)
            Text("$${it.precio}")
            Text(it.descripcion)

            Button(onClick = {
                CarritoManager.agregar(it)
                CarritoManager.guardar(context)
            }) {
                Text("Agregar al carrito")
            }
        }
    }
}