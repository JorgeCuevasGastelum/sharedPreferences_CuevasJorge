package cuevas.jorge.login.viewmodel

import androidx.lifecycle.ViewModel
import cuevas.jorge.login.data.models.Producto

class StockxViewModel: ViewModel() {

    private val listaProductos = listOf<Producto>(
        Producto(1, "YZY YS-01 Black", 1460.toDouble(), R., "chanclas"),
        Producto(2, "Jordan 5 Retro Wolf Grey", 5000.toDouble(), R., "desc"),
        Producto(1, "Jordan 4 Retro White Cement", 4600, R., "chanclas"),
        Producto(1, "Crocs Classic Clog Rayo McQueen", 1620, R., "chanclas"),
        Producto(1, "Jordan 1 Chicago Lost and Found", 4040, R., "chanclas"),
        Producto(1, "Birkenstock Boston", 2640, R., "chanclas"),
        Producto(1, "Nike Dunk Retro Low Panda", 1460, R., "chanclas"),
        Producto(1, "Yeezy Boost 350 v2 Zebra", 1460, R., "chanclas"),
        Producto(1, "Jordan 1 Retro Low Travis Scott", 11420, R., "chanclas"),
        Producto(1, "Nike Blaze Mid 77 Vintage", 1820, R., "chanclas")


    )


}