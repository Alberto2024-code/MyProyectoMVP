package com.example.myproyectomvp.Modelo

import com.example.myproyectomvp.Modelo.LoginResponse

import com.example.myproyectomvp.vista.MainActivity
import com.google.gson.stream.JsonToken
import retrofit2.Call
import  retrofit2.http.FormUrlEncoded
import retrofit2.http.Field

import retrofit2.http.POST



interface ApiService {



    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("usuario") usuario: String,
        @Field("contra") contra: String,
        @Field("tipo") tipo: Int
    ): Call<LoginResponse>


}