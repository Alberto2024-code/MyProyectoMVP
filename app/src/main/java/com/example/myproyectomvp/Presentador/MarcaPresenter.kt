package com.example.myproyectomvp.Presentador
import com.example.myproyectomvp.Contrato.MarcasContrac
import com.example.myproyectomvp.Modelo.DefaultResponse
import com.example.myproyectomvp.Modelo.Retrofit.api
class MarcaPresenter(
    private val view: MarcasContrac.View,
    private val model: MarcasContrac.Model
) : MarcasContrac.Presenter {

    override fun obtenerMarcas() {
        model.fetchMarcas(
            callback = { lista ->
                view.mostrarMarcas(lista ?: emptyList())
            },
            errorCallback = { error ->
                view.mostrarError(error ?: "Error desconocido")
            }
        )
    }

    override fun guardarMarca(nombre: String) {
        model.insertarMarca(nombre) { success, message ->
            if (success) {
                obtenerMarcas()
            } else {
                view.mostrarError(message)
            }
        }
    }

    override fun eliminarMarca(id: Int) {
        model.eliminarMarca(id) { success, mensaje ->
            if (success) obtenerMarcas()
            view.mostrarError(mensaje)
        }
    }

}

