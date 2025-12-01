package com.example.myproyectomvp.Modelo

import com.example.myproyectomvp.Contrato.ModelosContrac
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroModelo(private val api: ApiService) : ModelosContrac.Model {

    override fun fetchModelos(
        callback: (List<Modelo>?) -> Unit,   // <-- Lista de Modelo, no ModeloResponse
        errorCallback: (String?) -> Unit
    ) {
        api.getModelos().enqueue(object : Callback<ModeloResponse> {  // <-- Callback<ModelosResponse>
            override fun onResponse(
                call: Call<ModeloResponse>,  // <-- Call<ModelosResponse>
                response: Response<ModeloResponse>
            ) {
                if (response.isSuccessful) {
                    // Retornamos la lista de modelos
                    callback(response.body()?.modelos ?: emptyList())
                } else {
                    errorCallback("Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ModeloResponse>, t: Throwable) {
                errorCallback(t.message ?: "Error de conexión")
            }
        })
    }
}



