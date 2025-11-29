package com.example.myproyectomvp.Modelo

data class UsuarioResponse( val nombre: String,
                            val apellidoPaterno: String,
                            val apellidoMaterno: String,
                            val matricula: String,
                            val contrasenia: String,
                            val telefono: String,
                            val tipoUsuario: Int)
