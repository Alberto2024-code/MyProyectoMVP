package com.example.myproyectomvp.Presentador
import com.example.myproyectomvp.Contrato.DispositivosContrac
import com.example.myproyectomvp.Modelo.DispositivoModelo
import com.example.myproyectomvp.Modelo.Dispositivo
class DispositivosPresenter(
    private val view: DispositivosContrac.View,
    private val model: DispositivosContrac.Model
) : DispositivosContrac.Presenter {

    override fun cargarDispositivos() {
        view.mostrarCargando()
        model.obtenerDispositivos { success, lista, mensaje ->
            view.ocultarCargando()
            if (success && lista != null) {
                view.mostrarDispositivos(lista)
            } else {
                view.mostrarMensaje(mensaje ?: "Error al cargar dispositivos")
            }
        }
    }

    override fun registrarDispositivo(
        nombre: String,
        idTipoDispositivo: Int,
        idModelo: Int,
        idLaboratorio: Int,
        numeroInventario: String
    ) {
        view.mostrarCargando()
        model.registrarDispositivo(
            nombre,
            idTipoDispositivo,
            idModelo,
            idLaboratorio,
            numeroInventario
        ) { success, mensaje ->
            view.ocultarCargando()
            view.mostrarMensaje(mensaje)
            if (success) {
                // Opcional: limpiar campos, recargar lista, etc.
                cargarDispositivos()
            }
        }
    }
}