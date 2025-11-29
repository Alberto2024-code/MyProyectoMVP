package com.example.myproyectomvp.Modelo

import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private const val BASE_URL = "https://net06usa.nethostingsac.com/~bcorpsov/ApiMantenimientoComputo/php/"

    val api: ApiService by lazy {
        retrofit2.Retrofit.Builder()   // <-- usa el paquete completo
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}