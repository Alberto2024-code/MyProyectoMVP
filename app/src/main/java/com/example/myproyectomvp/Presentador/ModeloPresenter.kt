package com.example.myproyectomvp.Presentador
import com.example.myproyectomvp.Contrato.ModelosContrac
import com.example.myproyectomvp.Modelo.Modelo

class ModeloPresenter (
    private val view: ModelosContrac.View,
    private val model: ModelosContrac.Model
) : ModelosContrac.Presenter {

    override fun obtenerModelos() {
        model.fetchModelos(
            callback = { lista ->
                view.mostrarModelos(lista ?: emptyList()) // Si lista es null, pasamos una lista vacía
            },
            errorCallback = { error ->
                view.mostrarError(error ?: "Error desconocido") // Si error es null, mensaje por defecto
            }
        )

    }
   override fun guardarModelo(nombre: String, idMarca: Int) {
        model.guardarModelo(nombre, idMarca) { success, mensaje ->
            if (success) {
                view.mostrarError(mensaje) // o podrías hacer un método nuevo en View como mostrarMensaje
                obtenerModelos() // Refrescar tabla
            } else {
                view.mostrarError(mensaje)
            }
        }
    }


    override fun eliminarModelo(id: Int) {
        model.eliminarModelo(id) { success, mensaje ->
            if (success) {
                view.mostrarMensaje(mensaje) // o mostrarMensaje
                obtenerModelos() // Refrescar tabla
            } else {
                view.mostrarError(mensaje)
            }
        }
    }
}