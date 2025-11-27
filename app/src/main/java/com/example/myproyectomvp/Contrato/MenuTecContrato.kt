package com.example.myproyectomvp.Contrato
import com.example.myproyectomvp.Modelo.MenuTecUser
interface MenuTecContrato {
    interface View {
        fun showUser(user: MenuTecUser)
        fun showMessage(message: String)
    }

    interface Presenter {
        fun loadUser()
        fun logout()
        fun openMapa()
        fun openOrden()
        fun openRegistros()
    }
}