package com.example.myproyectomvp.vista

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Spinner
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myproyectomvp.Contrato.ModelosContrac
import com.example.myproyectomvp.Modelo.Modelo
import com.example.myproyectomvp.Presentador.ModeloPresenter
import com.example.myproyectomvp.Modelo.RegistroUserModelo
import com.example.myproyectomvp.Contrato.ModelosContrac.Model
import com.example.myproyectomvp.Modelo.ApiService
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import com.example.myproyectomvp.Modelo.DefaulResposnse
import com.example.myproyectomvp.Modelo.DefaultResponse
import com.example.myproyectomvp.Modelo.Marca
import com.example.myproyectomvp.Modelo.MarcaResponse
import com.example.myproyectomvp.Modelo.RegistroModelo
import com.example.myproyectomvp.Modelo.Retrofit
import com.example.myproyectomvp.R
import java.security.Guard


class ModelosActivity : AppCompatActivity(), ModelosContrac.View {

    private lateinit var presenter: ModelosContrac.Presenter
    private lateinit var tableLayout: TableLayout
    private lateinit var spinnerMarcas: Spinner
    private lateinit var edtNombreModelo: EditText
    private lateinit var marcasMap: Map<String, Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modelo_activity)

        // ---- Inicializar vistas ----
        tableLayout = findViewById(R.id.tblModelos)
        spinnerMarcas = findViewById(R.id.spnMarca)
        edtNombreModelo = findViewById(R.id.edtModelo)
        val btnRegresar = findViewById<ImageButton>(R.id.btnRegresar)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnRegresar.setOnClickListener { finish() }

        // ---- Configurar MVP ----
        val registroModelo = RegistroModelo(Retrofit.api)
        presenter = ModeloPresenter(this, registroModelo)

        // Cargar modelos al iniciar
        presenter.obtenerModelos()

        // Cargar spinner de marcas
        cargarSpinnerMarcas()

        // Guardar modelo
        btnGuardar.setOnClickListener {
            val nombre = edtNombreModelo.text.toString().trim()
            val marcaSeleccionada = spinnerMarcas.selectedItem?.toString() ?: ""
            val idMarca = marcasMap[marcaSeleccionada] ?: 0

            if (nombre.isEmpty() || idMarca == 0) {
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            presenter.guardarModelo(nombre, idMarca)
        }
    }

    // -----------------------------
    // Interfaz View - MVP
    // -----------------------------
    override fun mostrarModelos(modelos: List<Modelo>) {
        cargarTabla(modelos)
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    // -----------------------------
    // Tabla dinámica de modelos
    // -----------------------------
    private fun cargarTabla(modelos: List<Modelo>) {
        if (tableLayout.childCount > 1) {
            tableLayout.removeViews(1, tableLayout.childCount - 1)
        }

        for (modelo in modelos) {
            val fila = TableRow(this)
            fila.setPadding(8, 8, 8, 8)
            fila.setBackgroundColor(Color.WHITE)

            val tvId = TextView(this).apply {
                text = modelo.idModelo.toString()
                setPadding(10, 10, 10, 10)
                gravity = Gravity.CENTER
            }

            val tvModelo = TextView(this).apply {
                text = modelo.nombreModelo
                setPadding(10, 10, 10, 10)
            }

            val tvMarca = TextView(this).apply {
                text = modelo.nombreMarca
                setPadding(10, 10, 10, 10)
            }

            val accionesLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
            }

            val btnEliminar = Button(this).apply { text = "Eliminar" }
            btnEliminar.setOnClickListener {
                presenter.eliminarModelo(modelo.idModelo)
            }

            accionesLayout.addView(btnEliminar)

            fila.addView(tvId)
            fila.addView(tvModelo)
            fila.addView(tvMarca)
            fila.addView(accionesLayout)

            tableLayout.addView(fila)
        }
    }

    // -----------------------------
    // Cargar Spinner de marcas desde API
    // -----------------------------
    private fun cargarSpinnerMarcas() {
        Retrofit.api.getMarcas().enqueue(object : Callback<MarcaResponse> {
            override fun onResponse(call: Call<MarcaResponse>, response: Response<MarcaResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    val marcasList = response.body()?.marcas ?: emptyList()
                    val nombresMarcas = marcasList.map { it.marcas }
                    marcasMap = marcasList.associate { it.marcas to it.idMarcas }

                    val adapter = ArrayAdapter(
                        this@ModelosActivity,
                        android.R.layout.simple_spinner_item,
                        nombresMarcas
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerMarcas.adapter = adapter
                }
            }

            override fun onFailure(call: Call<MarcaResponse>, t: Throwable) {
                Toast.makeText(this@ModelosActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}