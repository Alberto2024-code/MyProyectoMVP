package com.example.myproyectomvp.vista



import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myproyectomvp.Contrato.LoginContrac
import com.example.myproyectomvp.Presentador.LoginPresenter
import com.example.myproyectomvp.R
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), LoginContrac.View {

    private lateinit var presenter: LoginContrac.Presenter
    private lateinit var edtUsuario: TextInputEditText
    private lateinit var edtContra: TextInputEditText
    private lateinit var spnTipo: Spinner
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        edtUsuario = findViewById(R.id.edtUsuario)
        edtContra = findViewById(R.id.edtContra)
        spnTipo = findViewById(R.id.spnTipo)
        btnLogin = findViewById(R.id.btnLogin)

        // Presenter
        presenter = LoginPresenter(this)

        // Spinner roles
        val tipos = listOf("Administrador", "Técnico")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            tipos
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnTipo.adapter = adapter

        // Botón login
        btnLogin.setOnClickListener {
            val usuario = edtUsuario.text.toString().trim()
            val contra = edtContra.text.toString().trim()
            val tipo = when (spnTipo.selectedItem.toString()) {
                "Administrador" -> 1
                "Técnico" -> 2
                else -> 0
            }


            if (usuario.isEmpty() || contra.isEmpty()) {
                showError("Por favor completa todos los campos")
                return@setOnClickListener
            }

            if (tipo == 0) {
                showError("Selecciona un tipo de usuario")
                return@setOnClickListener
            }

            presenter.validarLogin(usuario, contra,tipo)
        }
    }

    override fun showLoading() {
        Toast.makeText(this, "Validando...", Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {}

    override fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToMain(tipo: Int) {
        val intent = when (tipo) {
            // 1 -> Intent(this, MenuAdmActivity::class.java) // lo dejarás para después
            2 -> Intent(this, MenuTecActivity::class.java)
            else -> null
        }

        intent?.let {
            startActivity(it)
            finish() // Evitar volver al login con botón atrás
        } ?: showError("Tipo de usuario desconocido")
    }
}