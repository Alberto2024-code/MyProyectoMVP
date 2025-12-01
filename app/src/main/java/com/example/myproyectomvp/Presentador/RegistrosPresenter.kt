package com.example.myproyectomvp.Presentador

import com.example.myproyectomvp.Contrato.RegistrosUserContrac

class RegistrosPresenter(
    private val view: RegistrosUserContrac.View,
    private val model: RegistrosUserContrac.Model
) : RegistrosUserContrac.Presenter {

    override fun registrarUsuario(
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        matricula: String,
        contrasenia: String,
        telefono: String,
        idRoles: Int
    ) {
        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty() ||
            matricula.isEmpty() || contrasenia.isEmpty() || telefono.isEmpty() || idRoles == 0
        ) {
            view.mostrarError("Todos los campos son obligatorios")
            return
        }

        model.registrarBD(
            nombre,
            apellidoPaterno,
            apellidoMaterno,
            matricula,
            contrasenia,
            telefono,
            idRoles
        ) { response ->
            if (response.success) {
                view.usuarioRegistradoExitosamente()
            } else {
                view.mostrarError(response.message)
            }
        }
    }
}

