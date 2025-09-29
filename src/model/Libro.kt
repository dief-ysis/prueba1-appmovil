package model

open class Libro(
    val titulo: String,
    val autor: String,
    val precioBase: Double,
    val diasPrestamo: Int
) {
    open fun costoFinal(): Double = precioBase
}

class LibroFisico(
    titulo: String,
    autor: String,
    precioBase: Double,
    diasPrestamo: Int,
    val esReferencia: Boolean
) : Libro(titulo, autor, precioBase, diasPrestamo) {

    override fun costoFinal(): Double {
        return if (esReferencia) precioBase * 1.1 else precioBase
    }
}

class LibroDigital(
    titulo: String,
    autor: String,
    precioBase: Double,
    diasPrestamo: Int,
    val drm: Boolean
) : Libro(titulo, autor, precioBase, diasPrestamo) {

    override fun costoFinal(): Double {
        return if (drm) precioBase * 0.9 else precioBase
    }
}