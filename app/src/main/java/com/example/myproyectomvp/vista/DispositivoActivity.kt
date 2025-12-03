package com.example.myproyectomvp.vista
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myproyectomvp.R
import com.example.myproyectomvp.Contrato.DispositivosContrac
import com.example.myproyectomvp.Modelo.DispositivoModelo
import com.example.myproyectomvp.Presentador.DispositivosPresenter
import com.example.myproyectomvp.Modelo.Retrofit.api
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
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dispositivo)

        // Inicializar vistas
        etNombreDispositivo = findViewById(R.id.etNombreDispositivo)
        etNumeroInventario = findViewById(R.id.etNumeroInventario)
        spinnerTipo = findViewById(R.id.spinnerTipoDispositivo)
        spinnerModelo = findViewById(R.id.spinnerModelo)
        spinnerLaboratorio = findViewById(R.id.spinnerLaboratorio)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancelar)

        // -------------------------------
        //   CORRECCIÓN IMPORTANTE AQUÍ
        // -------------------------------
        val modelo = DispositivoModelo(api = api)
        presenter = DispositivosPresenter(this, modelo)

        // Botón Guardar
        btnGuardar.setOnClickListener {
            val nombre = etNombreDispositivo.text.toString()
            val numeroInventario = etNumeroInventario.text.toString()
            val tipoId = spinnerTipo.selectedItemPosition + 1
            val modeloId = spinnerModelo.selectedItemPosition + 1
            val laboratorioId = spinnerLaboratorio.selectedItemPosition + 1

            if (nombre.isNotEmpty() && numeroInventario.isNotEmpty()) {
                presenter.registrarDispositivo(
                    nombre,
                    tipoId,
                    modeloId,
                    laboratorioId,
                    numeroInventario
                )
            } else {
                mostrarMensaje("Completa todos los campos")
            }
        }

        btnCancelar.setOnClickListener {
            etNombreDispositivo.text.clear()
            etNumeroInventario.text.clear()
            spinnerTipo.setSelection(0)
            spinnerModelo.setSelection(0)
            spinnerLaboratorio.setSelection(0)
        }
    }

    // --------------------- Implementación de la vista ---------------------
    override fun mostrarDispositivos(lista: List<com.example.myproyectomvp.Modelo.Dispositivo>) {
        // Aquí podrías poner un RecyclerView
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun mostrarCargando() {
        // Si agregas un ProgressBar lo puedes mostrar aquí
    }

    override fun ocultarCargando() {
        // Ocultar ProgressBar
    }
}