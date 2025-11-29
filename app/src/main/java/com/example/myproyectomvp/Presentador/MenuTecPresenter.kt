package com.example.myproyectomvp.Presentador

import com.example.myproyectomvp.Modelo.ApiService
import com.example.myproyectomvp.Contrato.MenuTecContrato
import com.example.myproyectomvp.Modelo.MenuTecUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuTecPresenter(
    private val view: MenuTecContrato.View,
    private val api: ApiService
) : MenuTecContrato.Presenter {

    override fun loadUser() {
        val call = api.menuTec()
        call.enqueue(object : Callback<MenuTecUser> {
            override fun onResponse(call: Call<MenuTecUser>, response: Response<MenuTecUser>) {
                response.body()?.let { data ->
                    if (data.error != null) {
                        view.showMessage(data.error)
                    } else {
                        view.showUser(data)
                    }
                } ?: run {
                    view.showMessage("Error al cargar datos")
                }
            }

            override fun onFailure(call: Call<MenuTecUser>, t: Throwable) {
                view.showMessage("Error de conexión: ${t.message}")
            }
        })
    }

    override fun logout() {
        view.showMessage("Sesión cerrada")
        // Lógica para volver al LoginActivity
    }

    override fun openMapa() {
        view.showMessage("Abriendo Mapa de Sitio")
        // Abrir Activity/Fragment correspondiente
    }



    override fun openRegistros() {
        view.showMessage("Abriendo Registros")
        // Abrir Activity/Fragment correspondiente
    }
}

