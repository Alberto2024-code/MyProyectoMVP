package com.example.myproyectomvp.vista

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
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
import com.example.myproyectomvp.Modelo.RegistroModelo
import com.example.myproyectomvp.Modelo.Retrofit
import com.example.myproyectomvp.R



class ModelosActivity : AppCompatActivity(), ModelosContrac.View {

    private lateinit var presenter: ModelosContrac.Presenter
    private lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modelo_activity) // Tu XML real

        // TableLayout donde se mostrarán los modelos
        tableLayout = findViewById(R.id.tblModelos)

        val registroModelo = RegistroModelo(Retrofit.api)

// Crear el Presenter
        presenter = ModeloPresenter(this, registroModelo)


        // Cargar modelos al iniciar
        presenter.obtenerModelos()
    }

    // ==============================
    //  MÉTODO DEL MVP: MOSTRAR DATOS
    // ==============================
    override fun mostrarModelos(modelos: List<Modelo>) {
        cargarTabla(modelos)
    }

    // ==============================
    //  MÉTODO DEL MVP: MOSTRAR ERROR
    // ==============================
    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    // ==========================================
    // FUNCIÓN QUE ARMA LA TABLA DINÁMICAMENTE
    // ==========================================
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
}

