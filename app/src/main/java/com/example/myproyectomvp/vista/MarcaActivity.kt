package com.example.myproyectomvp.View

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myproyectomvp.Modelo.*
import com.example.myproyectomvp.Contrato.MarcasContrac
import com.example.myproyectomvp.Presentador.MarcaPresenter
import com.example.myproyectomvp.Modelo.RegistroMarcaModelo
import com.example.myproyectomvp.R

class MarcaActivity : AppCompatActivity(), MarcasContrac.View {

    private lateinit var edtMarca: EditText
    private lateinit var btnGuardar: Button
    private lateinit var tblMarcas: TableLayout
    private lateinit var presenter: MarcasContrac.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marcas_activity)

        edtMarca = findViewById(R.id.edtMarca)
        btnGuardar = findViewById(R.id.btnGuardar)
        tblMarcas = findViewById(R.id.tblMarcas)

        // Inicializamos el Presenter y pasamos el Modelo
        val model = RegistroMarcaModelo(Retrofit.api) // <-- Model
        presenter = MarcaPresenter(this, model)            // <-- 'this' es View
        presenter.obtenerMarcas()

        btnGuardar.setOnClickListener {
            val nombre = edtMarca.text.toString().trim()
            if (nombre.isNotEmpty()) {
                presenter.guardarMarca(nombre)
            } else {
                Toast.makeText(this, "Ingrese una marca", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun mostrarMarcas(marcas: List<Marca>) {
        // Limpiamos las filas existentes excepto la cabecera
        val headerCount = 1
        while (tblMarcas.childCount > headerCount) {
            tblMarcas.removeViewAt(headerCount)
        }

        // Agregamos cada marca
        marcas.forEach { marca ->
            val row = TableRow(this)
            row.setPadding(5, 5, 5, 5)

            val idText = TextView(this)
            idText.text = marca.idMarcas.toString()
            idText.gravity = android.view.Gravity.CENTER
            row.addView(idText)

            val nombreText = TextView(this)
            nombreText.text = marca.marcas
            nombreText.setPadding(10, 0, 0, 0)
            row.addView(nombreText)

            val accionesLayout = LinearLayout(this)
            accionesLayout.orientation = LinearLayout.HORIZONTAL
            accionesLayout.gravity = android.view.Gravity.CENTER

            val btnActualizar = TextView(this)
            btnActualizar.text = "Actualizar"
            btnActualizar.setTextColor(resources.getColor(R.color.black))
            btnActualizar.setPadding(0, 0, 10, 0)
            btnActualizar.setOnClickListener {
                edtMarca.setText(marca.marcas)
            }

            val btnEliminar = TextView(this)
            btnEliminar.text = "Eliminar"
            btnEliminar.setTextColor(resources.getColor(R.color.black))
            btnEliminar.setOnClickListener {
                presenter.eliminarMarca(marca.idMarcas)
            }

            accionesLayout.addView(btnActualizar)
            accionesLayout.addView(btnEliminar)
            row.addView(accionesLayout)

            tblMarcas.addView(row)
        }
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}
