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
}