package com.example.myproyectomvp.Modelo

data class Dispositivo(
    val idDispositivo: Int = 0,  // Si tu PHP devuelve ID
    val nombreDispositivo: String,
    val idTipoDispositivo: Int,
    val idModelo: Int,
    val idLaboratorio: Int,
    val numeroInventario: String
)
