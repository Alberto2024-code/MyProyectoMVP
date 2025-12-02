package com.example.myproyectomvp.View

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myproyectomvp.Modelo.Marca
import com.example.myproyectomvp.Modelo.RegistroMarcaModelo
import com.example.myproyectomvp.Contrato.MarcasContrac
import com.example.myproyectomvp.Presentador.MarcaPresenter
import com.example.myproyectomvp.R
import com.example.myproyectomvp.Modelo.Retrofit.api  // Asegúrate del import correcto

class MarcaActivity : AppCompatActivity(), MarcasContrac.View {

    private lateinit var edtMarca: EditText
    private lateinit var btnGuardar: Button
    private lateinit var tblMarcas: TableLayout
    private lateinit var presenter: MarcasContrac.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marcas_activity)

        // ---- Inicializar vistas ----
        edtMarca = findViewById(R.id.edtMarca)
        btnGuardar = findViewById(R.id.btnGuardar)
        tblMarcas = findViewById(R.id.tblMarcas)

        // ---- Configurar MVP ----
        val model = RegistroMarcaModelo(api)
        presenter = MarcaPresenter(this, model)

        // Cargar marcas al iniciar
        presenter.obtenerMarcas()

        // Guardar marca (aún no implementado)
        btnGuardar.setOnClickListener {
            val nombre = edtMarca.text.toString().trim()
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Ingrese una marca", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            presenter.guardarMarca(nombre)
        }
    }

    // ---------------------------------------------------------
    //         MÉTODOS DE LA INTERFAZ VIEW - MVP
    // ---------------------------------------------------------

    override fun mostrarMarcas(marcas: List<Marca>) {
        // Limpiar filas anteriores, dejando la cabecera
        while (tblMarcas.childCount > 1) {
            tblMarcas.removeViewAt(1)
        }

        // Agregar filas
        marcas.forEach { marca ->
            val row = TableRow(this)
            row.setPadding(6, 6, 6, 6)

            val idText = TextView(this).apply {
                text = marca.idMarcas.toString()
                gravity = android.view.Gravity.CENTER
            }

            val nombreText = TextView(this).apply {
                text = marca.marcas
                setPadding(12, 0, 0, 0)
            }

            // Botones de acciones (editar/eliminar)
            val accionesLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = android.view.Gravity.CENTER
                // Aquí puedes agregar botones dinámicamente
                val btnEditar = Button(this@MarcaActivity).apply {
                    text = "Editar"
                }
                val btnEliminar = Button(this@MarcaActivity).apply {
                    text = "Eliminar"
                }
                addView(btnEditar)
                addView(btnEliminar)
            }

            // Agregar vistas a la fila
            row.addView(idText)
            row.addView(nombreText)
            row.addView(accionesLayout)

            // Agregar la fila a la tabla
            tblMarcas.addView(row)
        }
    }


    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}

