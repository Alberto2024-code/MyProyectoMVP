package com.example.myproyectomvp.Modelo

import com.example.myproyectomvp.Modelo.Retrofit
import com.example.myproyectomvp.Contrato.LoginContrac
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModelo : LoginContrac.Modelo {

    override fun loginBD(
        usuario: String,
        contra: String,
        tipo: Int,
        callback: (LoginResponse) -> Unit
    ) {

        val call = Retrofit.api.login(
            usuario.trim(),
            contra.trim(),
            tipo
        )

        call.enqueue(object : Callback<LoginResponse> {

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                val body = response.body()

                if (!response.isSuccessful || body == null) {
                    callback(LoginResponse("fail", "Error en servidor"))
                    return
                }

                callback(body)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                callback(LoginResponse("fail", "No hay conexión"))
            }
        })
    }

}

