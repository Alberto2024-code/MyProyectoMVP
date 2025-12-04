package com.example.myproyectomvp.Modelo

import com.example.myproyectomvp.Modelo.LoginResponse

import com.example.myproyectomvp.vista.MainActivity
import com.google.gson.stream.JsonToken
import retrofit2.Call
import  retrofit2.http.FormUrlEncoded
import retrofit2.http.Field
import  retrofit2.http.GET

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



    @POST("menuTecnico.php")
    fun menuTec(): Call<MenuTecUser>



    @POST("menuAdm.php")
    fun menuAdm(): Call<MenuAdmResponse>

    @GET("Modelos.php")
    fun getModelos(): Call<ModeloResponse>
    @POST("Modelos.php")
    @FormUrlEncoded
    fun registrarModelo(
        @Field("nombreModelo") nombre: String,
        @Field("idMarca") idMarca: Int
    ): Call<DefaulResposnse>

    @POST("Modelos.php")
    @FormUrlEncoded
    fun eliminarModelo(
        @Field("idModelo") id: Int,
        @Field("accion") accion: String = "eliminar"
    ): Call<DefaulResposnse>



    @GET("Marcas.php")
    fun getMarcas(): Call<MarcaResponse>

    @FormUrlEncoded
    @POST("Marcas.php")
    fun insertarMarca(
        @Field("nombreMarca") nombre: String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("Marcas.php")
    fun eliminarMarca(
        @Field("idMarca") id: Int,
        @Field("accion") accion: String = "eliminar"
    ): Call<DefaultResponse>


    @GET("getDispositivos.php")
    fun getDispositivos(): Call<DispositivoResponse>
    @FormUrlEncoded
    @POST("registrodipositivos.php")
    fun registrarDispositivo(
        @Field("nombreDispositivo") nombreDispositivo: String,
        @Field("idTipoDispositivo") idTipoDispositivo: Int,
        @Field("idModelo") idModelo: Int,
        @Field("idLaboratorio") idLaboratorio: Int,
        @Field("numeroInventario") numeroInventario: String
    ): Call<DispositivoRes>
    @GET("getLaboratorios.php")
    fun getLaboratorios(): Call<LaboratorioResponse>
    @GET("getTipos.php")
    fun getTipos(): Call<TipoResponse>


}
