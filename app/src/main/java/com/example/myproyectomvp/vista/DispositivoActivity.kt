package com.example.myproyectomvp.vista
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myproyectomvp.Contrato.DispositivosContrac
import com.example.myproyectomvp.Modelo.Dispositivo
import com.example.myproyectomvp.Modelo.DispositivoModelo
import com.example.myproyectomvp.Modelo.ApiService
import com.example.myproyectomvp.Modelo.Retrofit
import com.example.myproyectomvp.Presentador.DispositivosPresenter
import com.example.myproyectomvp.R
import com.example.myproyectomvp.Modelo.Tipo
import com.example.myproyectomvp.Modelo.Modelo
import com.example.myproyectomvp.Modelo.Laboratorio
import com.example.myproyectomvp.Modelo.LaboratorioResponse
import com.example.myproyectomvp.Modelo.ModeloResponse
import com.example.myproyectomvp.Modelo.TipoResponse

class DispositivoActivity : AppCompatActivity(), DispositivosContrac.View {

    private lateinit var presenter: DispositivosContrac.Presenter

    // Campos del formulario
    private lateinit var etNombreDispositivo: EditText
    private lateinit var etNumeroInventario: EditText
    private lateinit var spinnerTipo: Spinner
    private lateinit var spinnerModelo: Spinner
    private lateinit var spinnerLaboratorio: Spinner
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button
    private lateinit var btnRegresar: ImageButton
    private lateinit var progressBar: ProgressBar

    // Listas para mantener los objetos completos
    private var listaTipos = listOf<Tipo>()
    private var listaModelos = listOf<Modelo>()
    private var listaLaboratorios = listOf<Laboratorio>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dispositivo)

        // Inicializar vistas
        etNombreDispositivo = findViewById(R.id.etNombreDispositivo)
        etNumeroInventario = findViewById(R.id.etNumeroInventario)
        spinnerTipo = findViewById(R.id.spinnerTipoDispositivo)
        spinnerModelo = findViewById(R.id.spnTipo)
        spinnerLaboratorio = findViewById(R.id.spinnerLaboratorio)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnRegresar = findViewById(R.id.btnRegresar)
        // Asegúrate de tenerlo en tu layout

        // Instancia de API y modelo
        val api = Retrofit.api
        val modelo = DispositivoModelo(api)
        presenter = DispositivosPresenter(this, modelo)

        // Cargar spinners desde API
        cargarSpinners()

        // Botón regresar
        btnRegresar.setOnClickListener { finish() }

        // Botón guardar
        btnGuardar.setOnClickListener {
            val nombre = etNombreDispositivo.text.toString().trim()
            val numeroInventario = etNumeroInventario.text.toString().trim()

            if (nombre.isEmpty() || numeroInventario.isEmpty()) {
                mostrarMensaje("Completa todos los campos")
                return@setOnClickListener
            }

            // Recuperar IDs reales
            val tipoId = listaTipos[spinnerTipo.selectedItemPosition].idTipoDispositivo
            val modeloId = listaModelos[spinnerModelo.selectedItemPosition].idModelo
            val laboratorioId = listaLaboratorios[spinnerLaboratorio.selectedItemPosition].idLaboratorio

            // Llamar al presenter para registrar
            presenter.registrarDispositivo(
                nombre,
                tipoId,
                modeloId,
                laboratorioId,
                numeroInventario
            )
        }

        // Botón cancelar
        btnCancelar.setOnClickListener {
            etNombreDispositivo.text.clear()
            etNumeroInventario.text.clear()
            spinnerTipo.setSelection(0)
            spinnerModelo.setSelection(0)
            spinnerLaboratorio.setSelection(0)
        }
    }

    private fun cargarSpinners() {
        val api = Retrofit.api

        // Spinner Tipo de Dispositivo
        api.getTipos().enqueue(object : retrofit2.Callback<TipoResponse> {
            override fun onResponse(call: retrofit2.Call<TipoResponse>, response: retrofit2.Response<TipoResponse>) {
                val body = response.body()
                if (body != null && body.success && body.tipos != null) {
                    listaTipos = body.tipos
                    val nombres = listaTipos.map { it.tipoDispositivo }
                    spinnerTipo.adapter = ArrayAdapter(this@DispositivoActivity, android.R.layout.simple_spinner_dropdown_item, nombres)
                }
            }
            override fun onFailure(call: retrofit2.Call<TipoResponse>, t: Throwable) {}
        })

        // Spinner Modelo
        api.getModelos().enqueue(object : retrofit2.Callback<ModeloResponse> {
            override fun onResponse(call: retrofit2.Call<ModeloResponse>, response: retrofit2.Response<ModeloResponse>) {
                val body = response.body()
                if (body != null && body.success && body.modelos != null) {
                    listaModelos = body.modelos
                    val nombres = listaModelos.map { it.nombreModelo }
                    spinnerModelo.adapter = ArrayAdapter(this@DispositivoActivity, android.R.layout.simple_spinner_dropdown_item, nombres)
                }
            }
            override fun onFailure(call: retrofit2.Call<ModeloResponse>, t: Throwable) {}
        })

        // Spinner Laboratorio
        api.getLaboratorios().enqueue(object : retrofit2.Callback<LaboratorioResponse> {
            override fun onResponse(call: retrofit2.Call<LaboratorioResponse>, response: retrofit2.Response<LaboratorioResponse>) {
                val body = response.body()
                if (body != null && body.success && body.laboratorios != null) {
                    listaLaboratorios = body.laboratorios
                    val nombres = listaLaboratorios.map { it.laboratorios }
                    spinnerLaboratorio.adapter = ArrayAdapter(this@DispositivoActivity, android.R.layout.simple_spinner_dropdown_item, nombres)
                }
            }
            override fun onFailure(call: retrofit2.Call<LaboratorioResponse>, t: Throwable) {}
        })
    }

    // --------------------- Implementación de la vista ---------------------
    override fun mostrarDispositivos(lista: List<Dispositivo>) {
        // Aquí podrías poner un RecyclerView si quieres mostrar la lista
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun mostrarCargando() {
        progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun ocultarCargando() {
        progressBar.visibility = ProgressBar.GONE
    }
}