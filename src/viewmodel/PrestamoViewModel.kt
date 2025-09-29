package viewmodel

import model.EstadoPrestamo
import model.Libro
import model.LibroDigital
import model.LibroFisico
import model.Prestamo
import service.GestorPrestamosService

class PrestamoViewModel(private val servicio: GestorPrestamosService) {

    private val catalogos = mutableListOf<Libro>()
    private val prestamos = mutableListOf<Prestamo>()

    fun inicializarCatalogo() {
        catalogos.addAll(
            listOf(
                LibroFisico("Estructuras de Datos", "Goodrich", 12990.0, 7, false),
                LibroFisico("Diccionario Enciclopédico", "Varios", 15990.0, 0, true),
                LibroDigital("Programación en Kotlin", "JetBrains", 9990.0, 10, true),
                LibroDigital("Algoritmos Básicos", "Cormen", 11990.0, 10, false)
            )
        )
    }

    fun listarCatalogo(): List<Libro> = catalogos

    fun solicitarPrestamo(libro: Libro, tipoUsuario: String, diasRetraso: Int = 0): Prestamo {
        val prestamo = Prestamo(libro, EstadoPrestamo.Pendiente, diasRetraso, tipoUsuario)
        prestamos.add(prestamo)
        return prestamo
    }

    fun procesarPrestamo(prestamo: Prestamo): EstadoPrestamo {
        return servicio.procesarPrestamo(prestamo)
    }

    fun devolverLibro(prestamo: Prestamo): EstadoPrestamo {
        return servicio.devolverLibro(prestamo)
    }

    fun calcularTotal(prestamo: Prestamo): Double {
        val costoBase = prestamo.libro.costoFinal()
        val descuento = when (prestamo.tipoUsuario) {
            "Estudiante" -> 0.10
            "Docente" -> 0.15
            else -> 0.0
        }
        val multa = if (prestamo.diasRetraso > 0) prestamo.diasRetraso * 1000.0 else 0.0
        return costoBase * (1 - descuento) + multa
    }

    fun obtenerPrestamos(): List<Prestamo> = prestamos

    fun librosPrestados(): List<Libro> {
        return prestamos
            .filter { it.estado == EstadoPrestamo.EnPrestamo || it.estado == EstadoPrestamo.Pendiente }
            .map { it.libro }
    }
}