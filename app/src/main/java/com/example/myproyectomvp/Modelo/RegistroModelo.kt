package com.example.myproyectomvp.Modelo

import com.example.myproyectomvp.Contrato.ModelosContrac
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroModelo(private val api: ApiService) : ModelosContrac.Model {

    override fun fetchModelos( callback: (List<Modelo>?) -> Unit,
        errorCallback: (String?) -> Unit
    ) {
        api.getModelos().enqueue(object : Callback<ModeloResponse> {  // <-- Callback<ModelosResponse>
            override fun onResponse(
                call: Call<ModeloResponse>,
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
     override fun guardarModelo(nombre: String, idMarca: Int, callback: (Boolean, String) -> Unit) {
        api.registrarModelo(nombre, idMarca).enqueue(object : Callback<DefaulResposnse> {
            override fun onResponse(call: Call<DefaulResposnse>, response: Response<DefaulResposnse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    callback(true, response.body()?.message ?: "Modelo guardado")
                } else {
                    callback(false, response.body()?.message ?: "Error al guardar modelo")
                }
            }

            override fun onFailure(call: Call<DefaulResposnse>, t: Throwable) {
                callback(false, t.message ?: "Error de conexión")
            }
        })
    }


    override fun eliminarModelo(id: Int, callback: (Boolean, String) -> Unit) {
        api.eliminarModelo(id, "eliminar").enqueue(object : Callback<DefaulResposnse> {
            override fun onResponse(
                call: Call<DefaulResposnse>,
                response: Response<DefaulResposnse>
            ) {
                if (response.isSuccessful && response.body()?.success == true) {
                    callback(true, response.body()?.message ?: "Modelo eliminado")
                } else {
                    callback(false, response.body()?.message ?: "Error al eliminar modelo")
                }
            }

            override fun onFailure(call: Call<DefaulResposnse>, t: Throwable) {
                callback(false, t.message ?: "Error de conexión")
            }
        })
    }
}



