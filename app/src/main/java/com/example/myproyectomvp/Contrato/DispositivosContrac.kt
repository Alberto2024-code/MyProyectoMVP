package com.example.myproyectomvp.Contrato

import com.example.myproyectomvp.Modelo.Dispositivo

interface DispositivosContrac {
    // La Vista (Activity o Fragment) implementa esto
    interface View {
        fun mostrarDispositivos(lista: List<Dispositivo>)
        fun mostrarMensaje(mensaje: String)
        fun mostrarCargando()
        fun ocultarCargando()
    }

    // El Presenter implementa esto
    interface Presenter {
        fun cargarDispositivos()
        fun registrarDispositivo(
            nombre: String,
            idTipoDispositivo: Int,
            idModelo: Int,
            idLaboratorio: Int,
            numeroInventario: String
        )
    }

    // Opcional: Modelo implementa esto
    interface Model {
        fun obtenerDispositivos(callback: (success: Boolean, lista: List<Dispositivo>?, mensaje: String?) -> Unit)
        fun registrarDispositivo(
            nombre: String,
            idTipoDispositivo: Int,
            idModelo: Int,
            idLaboratorio: Int,
            numeroInventario: String,
            callback: (success: Boolean, mensaje: String) -> Unit
        )
    }
}