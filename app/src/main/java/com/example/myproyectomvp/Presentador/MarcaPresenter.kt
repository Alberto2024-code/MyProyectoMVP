package com.example.myproyectomvp.Presentador
import com.example.myproyectomvp.Contrato.MarcasContrac
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
        // Por ahora vacío o log
        // view.mostrarError("Función guardarMarca aún no implementada")
    }

    override fun eliminarMarca(id: Int) {
        // Por ahora vacío o log
        // view.mostrarError("Función eliminarMarca aún no implementada")
    }
}
