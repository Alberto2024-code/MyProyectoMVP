package com.example.myproyectomvp.Contrato

import com.example.myproyectomvp.Modelo.Modelo

interface ModelosContrac {

    interface View {
        fun mostrarModelos(modelos: List<Modelo>)
        fun mostrarError(mensaje: String)
        fun mostrarMensaje(mensaje: String)
    }

    interface Presenter {
        fun obtenerModelos()

        fun guardarModelo(nombre: String, idMarca: Int)
        fun eliminarModelo(id: Int)
    }

    interface Model {
        fun fetchModelos(
            callback: (List<Modelo>?) -> Unit,
            errorCallback: (String?) -> Unit
        )
        fun guardarModelo(
            nombre: String,
            idMarca: Int,
            callback: (Boolean, String) -> Unit
        )

        fun eliminarModelo(
            id: Int,
            callback: (Boolean, String) -> Unit
        )
    }


}