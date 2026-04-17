package cuevas.jorge.login.data.models

import androidx.compose.runtime.mutableStateListOf

object CarritoManager {
    var carrito = mutableStateListOf<Producto>()

    fun agregar(producto: Producto) {
        carrito.add(producto)
    }

    fun total(): Double = carrito.sumOf { it.precio }
    fun cantidad(): Int = carrito.size
}