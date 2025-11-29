package com.example.myproyectomvp.vista

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myproyectomvp.Contrato.RegistrosUserContrac
import com.example.myproyectomvp.Modelo.RegistroUserModelo
import com.example.myproyectomvp.Presentador.RegistrosPresenter
import com.example.myproyectomvp.R

class RegistrosUsuarios : AppCompatActivity(), RegistrosUserContrac.View {

    private lateinit var presenter: RegistrosUserContrac.Presenter

    // UI
    private lateinit var edtNombre: EditText
    private lateinit var edtPaterno: EditText
    private lateinit var edtMaterno: EditText
    private lateinit var edtMatricula: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var spTipoUsuario: Spinner
    private lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrosusuarios)

        // Inicializar Presenter
        presenter = RegistrosPresenter(this, RegistroUserModelo())

        // Obtener vistas
        edtNombre = findViewById(R.id.txtNombre)
        edtPaterno = findViewById(R.id.txtPaterno)
        edtMaterno = findViewById(R.id.txtMaterno)
        edtMatricula = findViewById(R.id.txtMatricula)
        edtPassword = findViewById(R.id.txtPassword)
        edtTelefono = findViewById(R.id.txtTelefono)
        spTipoUsuario = findViewById(R.id.spTipoUsuario)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        // Llenar Spinner con opciones (ejemplo)
        val tipos = arrayOf("Selecciona tipo", "Administrador", "Usuario")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTipoUsuario.adapter = adapter

        // Acción del botón Registrar
        btnRegistrar.setOnClickListener {
            val nombre = edtNombre.text.toString()
            val paterno = edtPaterno.text.toString()
            val materno = edtMaterno.text.toString()
            val matricula = edtMatricula.text.toString()
            val password = edtPassword.text.toString()
            val telefono = edtTelefono.text.toString()
            val tipoUsuario = spTipoUsuario.selectedItemPosition // 0 = opción por defecto

            presenter.registrarUsuario(
                nombre,
                paterno,
                materno,
                matricula,
                password,
                telefono,
                tipoUsuario
            )
        }
    }

    // ======= IMPLEMENTACIÓN DE LA VISTA =======
    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun mostrarError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun usuarioRegistradoExitosamente() {
        Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
        // Opcional: limpiar campos
        edtNombre.text.clear()
        edtPaterno.text.clear()
        edtMaterno.text.clear()
        edtMatricula.text.clear()
        edtPassword.text.clear()
        edtTelefono.text.clear()
        spTipoUsuario.setSelection(0)
    }
}



