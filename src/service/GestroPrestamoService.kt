package service

import model.EstadoPrestamo
import model.LibroFisico
import model.Prestamo

class GestorPrestamosService {

    fun procesarPrestamo(prestamo: Prestamo): EstadoPrestamo {
        Thread.sleep(3000) // Reemplaza delay() por sleep()
        return if (prestamo.libro is LibroFisico && prestamo.libro.esReferencia) {
            EstadoPrestamo.Error("No se puede prestar un libro de referencia")
        } else {
            EstadoPrestamo.EnPrestamo
        }
    }

    fun devolverLibro(prestamo: Prestamo): EstadoPrestamo {
        Thread.sleep(3000)
        return EstadoPrestamo.Devuelto
    }
}