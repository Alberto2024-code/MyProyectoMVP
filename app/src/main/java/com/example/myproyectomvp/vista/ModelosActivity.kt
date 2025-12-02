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
import com.example.myproyectomvp.Modelo.DefaulResposnse
import com.example.myproyectomvp.Modelo.DefaultResponse
import com.example.myproyectomvp.Modelo.MarcaResponse
import com.example.myproyectomvp.Modelo.RegistroModelo
import com.example.myproyectomvp.Modelo.Retrofit
import com.example.myproyectomvp.R
import java.security.Guard


class ModelosActivity : AppCompatActivity(), ModelosContrac.View {

    private lateinit var presenter: ModelosContrac.Presenter
    private lateinit var tableLayout: TableLayout
    private lateinit var spinnerMarcas: Spinner





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modelo_activity) // Tu XML real

        // TableLayout donde se mostrarán los modelos
        tableLayout = findViewById(R.id.tblModelos)

        val registroModelo = RegistroModelo(Retrofit.api)
        spinnerMarcas = findViewById(R.id.spnMarca)

// Crear el Presenter
        presenter = ModeloPresenter(this, registroModelo)


        // Cargar modelos al iniciar
        presenter.obtenerModelos()

        cargarSpinnerMarcas()
    }


    override fun mostrarModelos(modelos: List<Modelo>) {
        cargarTabla(modelos)
        // Llenar Spinner con nombres de marcas
        val nombresMarcas = modelos.map { it.nombreMarca }.distinct() // evita duplicados

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            nombresMarcas
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerMarcas.adapter = adapter
    }


    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }


    private fun cargarTabla(modelos: List<Modelo>) {
        // Limpiar filas anteriores (dejando encabezado)
        if (tableLayout.childCount > 1) {
            tableLayout.removeViews(1, tableLayout.childCount - 1)
        }

        for (modelo in modelos) {
            val fila = TableRow(this)
            fila.setPadding(8, 8, 8, 8)
            fila.setBackgroundColor(Color.WHITE)

            // ID
            val tvId = TextView(this)
            tvId.text = modelo.idModelo.toString()
            tvId.setPadding(10, 10, 10, 10)
            tvId.gravity = Gravity.CENTER

            // Modelo
            val tvModelo = TextView(this)
            tvModelo.text = modelo.nombreModelo
            tvModelo.setPadding(10, 10, 10, 10)

            // Marca
            val tvMarca = TextView(this)
            tvMarca.text = modelo.nombreMarca
            tvMarca.setPadding(10, 10, 10, 10)

            // Agregar vistas a la fila
            fila.addView(tvId)
            fila.addView(tvModelo)
            fila.addView(tvMarca)

            // Aquí puedes agregar botones o acciones dinámicas si quieres
            val accionesLayout = TableRow(this)
            fila.addView(accionesLayout)

            // Agregar fila a la tabla
            tableLayout.addView(fila)
        }
    }
    private fun cargarSpinnerMarcas() {
        val api = Retrofit.api
        api.getMarcas().enqueue(object : Callback<MarcaResponse> {
            override fun onResponse(call: Call<MarcaResponse>, response: Response<MarcaResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    val marcasList = response.body()?.marcas ?: emptyList() // ✅ lista real

                    // Ahora sí puedes usar map
                    val nombresMarcas =
                        marcasList.map { it.marcas } // ✅ 'it' funciona porque marcasList es List<Marca>

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
                Toast.makeText(this@ModelosActivity, "Error: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
    private fun guardarModelo(nombreModelo: String, idMarca: Int) {
        val api = Retrofit.api

        api.registrarModelo(nombreModelo, idMarca).enqueue(object : Callback<DefaulResposnse> {
            override fun onResponse(call: Call<DefaulResposnse>, response: Response<DefaulResposnse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    Toast.makeText(
                        this@ModelosActivity,
                        response.body()?.message ?: "Modelo guardado",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Refrescar tabla
                    presenter.obtenerModelos()
                } else {
                    Toast.makeText(
                        this@ModelosActivity,
                        response.body()?.message ?: "Error desconocido",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<DefaulResposnse>, t: Throwable) {
                Toast.makeText(this@ModelosActivity, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}

