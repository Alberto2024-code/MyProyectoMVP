package com.example.myproyectomvp.Modelo

data class Dispositivo(
    val idDispositivo: Int,
    val tipoDispositivo: String,
    val nombreDispositivo: String,
    val modelo: String?,
    val laboratorio: String?,
    val numeroInventario: String
)
