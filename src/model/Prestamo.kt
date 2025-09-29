package model

data class Prestamo(
    val libro: Libro,
    var estado: EstadoPrestamo,
    val diasRetraso: Int = 0,
    val tipoUsuario: String = "Externo"
)