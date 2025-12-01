package com.example.myproyectomvp.Contrato

import com.example.myproyectomvp.Modelo.Modelo

interface ModelosContrac {

    interface View {
        fun mostrarModelos(modelos: List<Modelo>)
        fun mostrarError(mensaje: String)
    }

    interface Presenter {
        fun obtenerModelos()
    }

    interface Model {
        fun fetchModelos(
            callback: (List<Modelo>?) -> Unit,
            errorCallback: (String?) -> Unit
        )
    }


}