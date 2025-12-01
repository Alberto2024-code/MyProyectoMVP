package com.example.myproyectomvp.Modelo

import com.example.myproyectomvp.Contrato.RegistrosUserContrac
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroUserModelo : RegistrosUserContrac.Model {

    override fun registrarBD(
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        matricula: String,
        contrasenia: String,
        telefono: String,
        idRoles: Int,
        callback: (RegistroUserResponse) -> Unit
    ) {
        val call = Retrofit.api.Registro(
            nombre.trim(),
            apellidoPaterno.trim(),
            apellidoMaterno.trim(),
            matricula.trim(),
            contrasenia.trim(),
            telefono.trim(),
            idRoles
        )

        call.enqueue(object : Callback<RegistroUserResponse> {
            override fun onResponse(
                call: Call<RegistroUserResponse>,
                response: Response<RegistroUserResponse>
            ) {
                val body = response.body()
                if (!response.isSuccessful || body == null) {
                    callback(RegistroUserResponse(false, "Error en el servidor"))
                    return
                }
                callback(body)
            }

            override fun onFailure(call: Call<RegistroUserResponse>, t: Throwable) {
                callback(RegistroUserResponse(false, "Sin conexión al servidor"))
            }
        })
    }
}


