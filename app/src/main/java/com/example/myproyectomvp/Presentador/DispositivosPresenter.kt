package com.example.myproyectomvp.Presentador
import com.example.myproyectomvp.Contrato.DispositivosContrac
import com.example.myproyectomvp.Modelo.DispositivoModelo
import com.example.myproyectomvp.Modelo.Dispositivo
class DispositivosPresenter (
    private val view: DispositivosContrac.View,
    private val modelo: DispositivoModelo
) : DispositivosContrac.Presenter {

    // Cargar la lista de dispositivos
    override fun cargarDispositivos() {
        view.mostrarCargando()
        modelo.obtenerDispositivos { success, lista, mensaje ->
            view.ocultarCargando()
            if(success) {
                view.mostrarDispositivos(lista ?: emptyList())
            } else {
                view.mostrarMensaje(mensaje ?: "Error al cargar dispositivos")
            }
        }
    }

    // Registrar un nuevo dispositivo
    override fun registrarDispositivo(
        nombre: String,
        idTipoDispositivo: Int,
        idModelo: Int,
        idLaboratorio: Int,
        numeroInventario: String
    ) {
        view.mostrarCargando()
        modelo.registrarDispositivo(nombre, idTipoDispositivo, idModelo, idLaboratorio, numeroInventario) { success, mensaje ->
            view.ocultarCargando()
            view.mostrarMensaje(mensaje)
            if(success) {
                // Si quieres, recarga la lista después de registrar
                cargarDispositivos()
            }
        }
    }
}