package com.example.myproyectomvp.Contrato

interface LoginContrac {interface View {
    fun showLoading()
    fun hideLoading()
    fun showError(msg: String)
    fun navigateToMain(tipo: Int)
}

    // Lo que hace el presentador (procesa el login)
    interface Presenter {
        fun validarLogin(usuario: String, contra: String, tipo: Int)
    }

    // Lo que hace el Modelo (habla con BD)
    interface Modelo {
        fun loginBD(usuario: String, contra: String, tipo: Int, callback: (Boolean) -> Unit)
    }
}