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
        tipoUsuario: Int
    ) {

        // Validaciones básicas
        if (nombre.isEmpty() || apellidoPaterno.isEmpty() || apellidoMaterno.isEmpty() ||
            matricula.isEmpty() || contrasenia.isEmpty() || telefono.isEmpty() || tipoUsuario == 0
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
            tipoUsuario
        ) { response ->

            if (response.message == "success") {
                view.usuarioRegistradoExitosamente()
            } else {
                view.mostrarError(response.message)
            }
        }
    }
}
