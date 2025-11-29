package com.example.myproyectomvp.Contrato

import com.example.myproyectomvp.Modelo.MenuAdmResponse
import com.example.myproyectomvp.Modelo.MenuTecUser

interface MenuAdminContrac {

    interface view
    {
        fun showUser(user: MenuAdmResponse)
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