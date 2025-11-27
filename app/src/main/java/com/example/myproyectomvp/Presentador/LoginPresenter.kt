package com.example.myproyectomvp.Presentador

import com.example.myproyectomvp.Modelo.LoginModelo
import com.example.myproyectomvp.Contrato.LoginContrac

class LoginPresenter(
    private val view: LoginContrac.View,
    private val model: LoginContrac.Modelo = LoginModelo()
) : LoginContrac.Presenter {

    override fun validarLogin(usuario: String, contra: String, tipo: Int) {

        if (usuario.isEmpty() || contra.isEmpty()) {
            view.showError("Rellena todos los campos")
            return
        }

        view.showLoading()

        model.loginBD(usuario, contra, tipo) { response ->

            view.hideLoading()

            if (response.status == "success") {
                // PHP manda el tipo correcto, úsalo
                val tipoUsuario = response.tipo ?: tipo
                view.navigateToMain(tipoUsuario)
            } else {
                view.showError(response.mensaje ?: "Usuario o contraseña incorrectos")
            }
        }
    }
}


