package cuevas.jorge.login.data.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CarritoManager {
    private const val PREFS = "carrito_prefs"
    private const val KEY = "carrito"

    var carrito: MutableList<Producto> = mutableListOf()

    fun agregar(producto: Producto) {
        carrito.add(producto)
    }

    fun total(): Double = carrito.sumOf { it.precio }

    fun cantidad(): Int = carrito.size

    fun guardar(context: Context) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val json = Gson().toJson(carrito)
        prefs.edit().putString(KEY, json).apply()
    }

    fun cargar(context: Context) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY, null)
        if (json != null) {
            val tipo = object : TypeToken<MutableList<Producto>>() {}.type
            carrito = Gson().fromJson(json, tipo)
        }
    }

    fun limpiar(context: Context) {
        carrito.clear()

        val prefs = context.getSharedPreferences("carrito_prefs", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }
}
