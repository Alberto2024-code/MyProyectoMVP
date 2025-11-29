package com.example.myproyectomvp.Contrato

import com.example.myproyectomvp.Modelo.RegistroUserResponse
import com.example.myproyectomvp.Modelo.UsuarioResponse

interface RegistrosUserContrac {

    interface View {
        fun mostrarMensaje(mensaje: String)
        fun usuarioRegistradoExitosamente()
        fun mostrarError(error: String)
    }

    interface Presenter {
        fun registrarUsuario(
            nombre: String,
            apellidoPaterno: String,
            apellidoMaterno: String,
            matricula: String,
            contrasenia: String,
            telefono: String,
            tipoUsuario: Int
        )
    }

    interface Model {
        fun registrarBD(
            nombre: String,
            apellidoPaterno: String,
            apellidoMaterno: String,
            matricula: String,
            contrasenia: String,
            telefono: String,
            tipoUsuario: Int,
            callback: (RegistroUserResponse) -> Unit
        )
    }
}
