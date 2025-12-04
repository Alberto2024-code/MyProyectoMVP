package com.example.myproyectomvp.Modelo

data class TipoResponse(val success: Boolean,
                        val tipos: List<Tipo>?,
                        val message: String?)
