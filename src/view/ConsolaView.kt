package view

import model.EstadoPrestamo
import model.Prestamo
import viewmodel.PrestamoViewModel

class ConsolaView(private val viewModel: PrestamoViewModel) {

    fun mostrarCatalogo() {
        println("=== CATÁLOGO DE LIBROS ===")
        viewModel.listarCatalogo().forEachIndexed { index, libro ->
            println("${index + 1}. ${libro.titulo} - ${libro.autor} - $${libro.precioBase}")
        }
        println()
    }

    fun solicitarPrestamo() {
        print("Seleccione libro (número): ")
        val num = readLine()?.toIntOrNull() ?: return
        val libro = viewModel.listarCatalogo().getOrNull(num - 1) ?: return

        print("Tipo de usuario (Estudiante/Docente/Externo): ")
        val tipo = readLine() ?: "Externo"

        print("Días de retraso (0 si no hay): ")
        val retraso = readLine()?.toIntOrNull() ?: 0

        val prestamo = viewModel.solicitarPrestamo(libro, tipo, retraso)

        println("Procesando préstamo...")
        val estado = viewModel.procesarPrestamo(prestamo)
        prestamo.estado = estado

        when (estado) {
            is EstadoPrestamo.Error -> println("❌ Error: ${estado.mensaje}")
            else -> {
                println("✅ Préstamo procesado: ${estado.javaClass.simpleName}")
                mostrarResumen(prestamo)
            }
        }
    }

    private fun mostrarResumen(prestamo: Prestamo) {
        println("\n=== RESUMEN DE PRÉSTAMO ===")
        println("Libro: ${prestamo.libro.titulo}")
        println("Usuario: ${prestamo.tipoUsuario}")
        println("Días retraso: ${prestamo.diasRetraso}")
        println("Subtotal: $${prestamo.libro.costoFinal()}")
        println("Total a pagar: $${viewModel.calcularTotal(prestamo)}")
        println("Estado: ${prestamo.estado}")
        println()
    }

    fun mostrarReporte() {
        println("=== REPORTE DE PRÉSTAMOS ===")
        val prestamos = viewModel.obtenerPrestamos()
        if (prestamos.isEmpty()) {
            println("No hay préstamos registrados.")
        } else {
            prestamos.forEachIndexed { i, p ->
                println("${i + 1}. ${p.libro.titulo} - ${p.estado}")
            }
        }
        println()
    }
}