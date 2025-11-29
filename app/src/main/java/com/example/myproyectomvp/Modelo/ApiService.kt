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


    @FormUrlEncoded
    @POST("registrosUsua.php")
    fun Registro(
        @Field("nombre") nombre: String,
        @Field("apellidoPaterno") apellidoPaterno: String,
        @Field("apellidoMaterno") apellidoMaterno: String,
        @Field("matricula") matricula: String,
        @Field("contrasenia") contrasenia: String,
        @Field("telefono") telefono: String,
        @Field("tipoUsuario") tipoUsuario: Int
    ): Call<RegistroUserResponse>

    interface TecnicoApi
        @POST("menuTecnico.php")
        fun menuTec(): Call<MenuTecUser>

    interface AdminApi
    @POST("menuAdm.php")
    fun menuAdm(): Call<MenuAdmResponse>

}
