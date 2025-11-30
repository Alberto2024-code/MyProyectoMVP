package com.example.myproyectomvp.Contrato

import com.example.myproyectomvp.Modelo.ModeloResponse

interface ModelosContrac {

    interface View {
        fun mostrarModelos(modelos: List<com.example.myproyectomvp.Modelo.Modelo>)
        fun mostrarError(mensaje: String)
    }

    interface Presenter {
        fun obtenerModelos()
    }

    interface Model {
        fun fetchModelos(callback: (ModeloResponse?) -> Unit, errorCallback: (String) -> Unit)
    }
}