package com.example.myproyectomvp.Presentador
import com.example.myproyectomvp.Contrato.MenuTecContrato
import com.example.myproyectomvp.Modelo.MenuTecUser
class MenuTecPresenter (private val view: MenuTecContrato.View) : MenuTecContrato.Presenter {

    override fun loadUser() {
        // Usuario de ejemplo
        val user = MenuTecUser(nombre = "Alberto", mensaje = "Bienvenido al menú de técnico")
        view.showUser(user)
    }

    override fun logout() {
        view.showMessage("Sesión cerrada")
        // Aquí puedes agregar lógica para volver al Login
    }

    override fun openMapa() {
        view.showMessage("Abriendo Mapa de Sitio")
    }

    override fun openOrden() {
        view.showMessage("Abriendo Orden de Trabajo")
    }

    override fun openRegistros() {
        view.showMessage("Abriendo Registros")
    }
}