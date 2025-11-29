package com.example.myproyectomvp.Presentador

import com.example.myproyectomvp.Contrato.MenuAdminContrac
import com.example.myproyectomvp.Modelo.ApiService
import com.example.myproyectomvp.Modelo.MenuAdmResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuAdmPresenter(
    private val view: MenuAdminContrac.view,
    private val api: ApiService
) : MenuAdminContrac.Presenter {

    override fun loadUser() {
        val call = api.menuAdm()   // ← usa tu endpoint de administrador
        call.enqueue(object : Callback<MenuAdmResponse> {

            override fun onResponse(
                call: Call<MenuAdmResponse>,
                response: Response<MenuAdmResponse>
            ) {
                val data = response.body()

                if (data == null) {
                    view.showMessage("Error al cargar datos")
                    return
                }

                if (data.error != null) {
                    view.showMessage(data.error)
                } else {
                    view.showUser(data)
                }
            }

            override fun onFailure(call: Call<MenuAdmResponse>, t: Throwable) {
                view.showMessage("Error de conexión: ${t.message}")
            }
        })
    }

    override fun logout() {
        view.showMessage("Sesión cerrada")
        // Aquí puedes enviar al login si quieres
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
