package com.example.myproyectomvp.Contrato

import com.example.myproyectomvp.Modelo.Marca

interface MarcasContrac {

    interface View {
        fun mostrarMarcas(marcas: List<Marca>)
        fun mostrarError(mensaje: String)
    }

    interface Presenter {
        fun obtenerMarcas()
        fun guardarMarca(nombre: String)
        fun eliminarMarca(id: Int)
    }

    interface Model {
        fun fetchMarcas(
            callback: (List<Marca>?) -> Unit,
            errorCallback: (String?) -> Unit
        )
        fun insertarMarca(
            nombre: String,
            callback: (Boolean, String) -> Unit
        )
        fun eliminarMarca(id: Int, callback: (Boolean, String) -> Unit)
    }
}