package cuevas.jorge.login.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cuevas.jorge.login.DataStoreManager
import cuevas.jorge.login.data.models.CarritoManager
import cuevas.jorge.login.data.models.ProductosData
import kotlinx.coroutines.launch

@Composable
fun PantallaDetalle(id: Int, onBack: () -> Unit) {
    val producto = ProductosData.obtenerProductoPorId(id)
    val context = LocalContext.current

    // Necesitamos el scope para ejecutar la corrutina al hacer click
    val scope = rememberCoroutineScope()

    // Instanciamos nuestro nuevo manejador
    val prefs = remember { DataStoreManager(context) }

    producto?.let { it ->
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text(it.nombre, style = MaterialTheme.typography.titleLarge)
            Text("$${it.precio}")
            Text(it.descripcion)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                // Agregamos el producto al objeto en memoria
                CarritoManager.agregar(it)

                // Lanzamos la corrutina para persistir la lista completa en DataStore
                scope.launch {
                    prefs.guardarCarrito(CarritoManager.carrito)
                }
            }) {
                Text("Agregar al carrito")
            }
        }
    }
}