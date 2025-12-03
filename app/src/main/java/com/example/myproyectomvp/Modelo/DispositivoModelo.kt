package com.example.myproyectomvp.Modelo
import com.example.myproyectomvp.Contrato.DispositivosContrac
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class DispositivoModelo (private val api: ApiService) : DispositivosContrac.Model {

    // Obtener lista de dispositivos
    override fun obtenerDispositivos(callback: (success: Boolean, lista: List<Dispositivo>?, mensaje: String?) -> Unit) {
        api.getDispositivos().enqueue(object : Callback<DispositivoResponse> {
            override fun onResponse(call: Call<DispositivoResponse>, response: Response<DispositivoResponse>) {
                val body = response.body()
                if (body != null && body.success) {
                    callback(true, body.dispositivos, null)
                } else {
                    callback(false, null, body?.message ?: "Error desconocido")
                }
            }

            override fun onFailure(call: Call<DispositivoResponse>, t: Throwable) {
                callback(false, null, t.localizedMessage ?: "Error de conexión")
            }
        })
    }

    // Registrar un dispositivo
    override fun registrarDispositivo(
        nombre: String,
        idTipoDispositivo: Int,
        idModelo: Int,
        idLaboratorio: Int,
        numeroInventario: String,
        callback: (success: Boolean, mensaje: String) -> Unit
    ) {
        api.registrarDispositivo(nombre, idTipoDispositivo, idModelo, idLaboratorio, numeroInventario)
            .enqueue(object : Callback<RegistroRes> {
                override fun onResponse(call: Call<RegistroRes>, response: Response<RegistroRes>) {
                    val body = response.body()
                    if (body != null) callback(body.success, body.message)
                    else callback(false, "Error desconocido")
                }

                override fun onFailure(call: Call<RegistroRes>, t: Throwable) {
                    callback(false, t.localizedMessage ?: "Error de conexión")
                }
            })
    }
}