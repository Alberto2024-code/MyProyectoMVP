package com.example.myproyectomvp.Modelo

data class DispositivoResponse( val success: Boolean,
                                val dispositivos: List<Dispositivo>?,
                                val message: String?)

