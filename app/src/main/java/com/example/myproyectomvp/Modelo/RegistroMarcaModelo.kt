package com.example.myproyectomvp.Modelo

import com.example.myproyectomvp.Contrato.MarcasContrac
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroMarcaModelo(private val api: ApiService) : MarcasContrac.Model {

    override fun fetchMarcas(
        callback: (List<Marca>?) -> Unit,
        errorCallback: (String?) -> Unit
    ) {
        api.getMarcas().enqueue(object : Callback<MarcaResponse> {
            override fun onResponse(
                call: Call<MarcaResponse>,
                response: Response<MarcaResponse>
            ) {
                if (response.isSuccessful) {

                    val body = response.body()

                    if (body != null && body.success) {
                        callback(body.marcas)
                    } else {
                        errorCallback("Error del servidor: success=false")
                    }

                } else {
                    errorCallback("Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MarcaResponse>, t: Throwable) {
                errorCallback(t.message ?: "Error de conexión")
            }

        })

    }

    override fun insertarMarca(nombre: String, callback: (Boolean, String) -> Unit) {
        api.insertarMarca(nombre).enqueue(object : Callback<DefaultResponse> {
            override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    callback(body?.success == true, if (body?.success == true) "OK" else "Error desconocido")
                } else {
                    callback(false, "Error en la respuesta")
                }
            }

            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                callback(false, t.message ?: "Error de conexión")
            }
        })
    }


}
