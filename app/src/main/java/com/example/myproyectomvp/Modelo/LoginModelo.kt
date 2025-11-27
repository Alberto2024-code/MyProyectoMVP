package com.example.myproyectomvp.Modelo
import com.example.myproyectomvp.Contrato.LoginContrac
import  com.example.myproyectomvp.Modelo.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class LoginModelo : LoginContrac.Modelo {

    override fun loginBD(
        usuario: String,
        contra: String,
        tipo: Int,
        callback: (Boolean) -> Unit
    ) {
        val call = Retrofit.api.login(usuario.trim(), contra.trim(), tipo)

        call.enqueue(object : Callback<LoginResponse> {

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                val body = response.body()

                if (!response.isSuccessful || body == null) {
                    callback(false)
                    return
                }

                val loginCorrecto = body.status == "success"
                callback(loginCorrecto)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                println("Retrofit ERROR: ${t.message}")
                callback(false)
            }
        })
    }
}
