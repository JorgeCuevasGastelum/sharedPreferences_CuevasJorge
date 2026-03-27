package cuevas.jorge.login.data.models

import cuevas.jorge.login.R

object ProductosData {

    private val listaProductos = listOf<Producto>(
        Producto(1, "Yeezy YS-01 Black", 1460.00, R.drawable.yeezyslide,
            "Sandalias Yeezy de diseño minimalista en color negro. Fabricadas con espuma EVA para máxima comodidad y ligereza, ideales para uso diario con un estilo moderno y relajado."),

        Producto(2, "Jordan 5 Retro Wolf Grey", 5000.00, R.drawable.jordanwolfgrey,
            "Tenis Air Jordan 5 en tonalidad Wolf Grey con detalles premium. Ofrecen gran soporte, amortiguación y un diseño icónico inspirado en el legado del básquetbol."),

        Producto(3, "Jordan 4 Retro White Cement", 4600.00, R.drawable.jordanwhitecement,
            "Clásicos Air Jordan 4 White Cement con su distintivo diseño retro. Combinan materiales de alta calidad con una estética legendaria para los amantes de los sneakers."),

        Producto(4, "Crocs Classic Clog Rayo McQueen", 1620.00, R.drawable.crocs,
            "Crocs edición especial Rayo McQueen, ligeros y cómodos. Ideales para uso casual, destacan por su diseño divertido y su ajuste ergonómico."),

        Producto(5, "Jordan 1 Chicago Lost and Found", 4040.00, R.drawable.jordanchicago,
            "Air Jordan 1 Chicago Lost and Found con un estilo vintage único. Representan la historia del sneaker con acabados envejecidos y gran comodidad."),

        Producto(6, "Birkenstock Boston", 2640.00, R.drawable.birkenstock,
            "Sandalias Birkenstock Boston con diseño clásico y plantilla anatómica. Brindan soporte superior y confort para uso prolongado."),

        Producto(7, "Nike Dunk Retro Low Panda", 1460.00, R.drawable.dunkpanda,
            "Nike Dunk Low Panda con combinación en blanco y negro. Un básico versátil que combina con cualquier outfit, ideal para uso urbano diario."),

        Producto(8, "Yeezy Boost 350 v2 Zebra", 1460.00, R.drawable.yeezyzebra,
            "Yeezy Boost 350 v2 Zebra con patrón distintivo y tecnología Boost. Máxima comodidad y estilo moderno para los amantes del streetwear."),

        Producto(9, "Jordan 1 Retro Low Travis Scott", 11420.00, R.drawable.jordanlow,
            "Air Jordan 1 Low Travis Scott con diseño exclusivo y detalles únicos. Una pieza premium muy buscada por coleccionistas y fans del sneaker culture."),

        Producto(10, "Nike Blaze Mid 77 Vintage", 1820.00, R.drawable.dunkpanda,
            "Nike Blazer Mid 77 Vintage con estilo retro. Diseño clásico con materiales duraderos y gran comodidad para el día a día.")


    )

    fun filtrarProductos(query: String): List<Producto> {
        return if (query.isBlank()) listaProductos
        else listaProductos.filter { it.nombre.contains(query, ignoreCase = true) }
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return listaProductos.find { it.id == id }
    }


}