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
                    callback(response.body()?.marcas ?: emptyList())
                } else {
                    errorCallback("Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MarcaResponse>, t: Throwable) {
                errorCallback(t.message ?: "Error de conexión")
            }
        })
    }
}