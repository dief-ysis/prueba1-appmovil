import service.GestorPrestamosService
import view.ConsolaView
import viewmodel.PrestamoViewModel

fun main() {
    val servicio = GestorPrestamosService()
    val viewModel = PrestamoViewModel(servicio)
    val vista = ConsolaView(viewModel)

    viewModel.inicializarCatalogo()

    var opcion: Int
    do {
        println("1. Ver catálogo")
        println("2. Solicitar préstamo")
        println("3. Ver reporte de préstamos")
        println("0. Salir")
        print("Opción: ")
        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> vista.mostrarCatalogo()
            2 -> vista.solicitarPrestamo()
            3 -> vista.mostrarReporte()
        }
    } while (opcion != 0)
}