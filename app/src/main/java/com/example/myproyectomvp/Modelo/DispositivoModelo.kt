package com.example.myproyectomvp.Modelo
import com.example.myproyectomvp.Contrato.DispositivosContrac
import com.example.myproyectomvp.Modelo.DefaultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class DispositivoModelo(private val api: ApiService) : DispositivosContrac.Model {


    override fun obtenerDispositivos(callback: (success: Boolean, lista: List<Dispositivo>?, mensaje: String?) -> Unit) {
        api.getDispositivos().enqueue(object : Callback<DispositivoResponse> {
            override fun onResponse(call: Call<DispositivoResponse>, response: Response<DispositivoResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!
                    callback(body.success, body.dispositivos, body.message)
                } else {
                    callback(false, null, "Error al obtener dispositivos")
                }
            }

            override fun onFailure(call: Call<DispositivoResponse>, t: Throwable) {
                callback(false, null, t.message ?: "Error de conexión")
            }
        })
    }

    // Registrar un nuevo dispositivo
    override fun registrarDispositivo(
        nombre: String,
        idTipoDispositivo: Int,
        idModelo: Int,
        idLaboratorio: Int,
        numeroInventario: String,
        callback: (success: Boolean, mensaje: String) -> Unit
    ) {
        api.registrarDispositivo(
            nombre,
            idTipoDispositivo,
            idModelo,
            idLaboratorio,
            numeroInventario
        ).enqueue(object : Callback<DispositivoRes> {
            override fun onResponse(call: Call<DispositivoRes>, response: Response<DispositivoRes>) {
                if (response.isSuccessful && response.body() != null) {
                    val body = response.body()!!
                    callback(body.success, body.message)
                } else {
                    callback(false, "Error al registrar dispositivo")
                }
            }

            override fun onFailure(call: Call<DispositivoRes>, t: Throwable) {
                callback(false, t.message ?: "Error de conexión")
            }
        })
    }
}