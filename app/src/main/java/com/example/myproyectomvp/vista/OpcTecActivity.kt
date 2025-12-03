package com.example.myproyectomvp.vista

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myproyectomvp.R
import com.example.myproyectomvp.View.MarcaActivity
import com.google.android.material.navigation.NavigationView

class OpcTecActivity : AppCompatActivity() {

    private lateinit var drawer: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Desactivar modo oscuro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)

        // Asignar el layout XML
        setContentView(R.layout.registrostec) // <-- Cambia por tu layout si se llama distinto

        // Inicializar Drawer y NavigationView
        drawer = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.navView)

        // Configurar Toolbar con hamburguesa
        val toolbar = findViewById<Toolbar>(R.id.toolbarRegistros)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            drawer.openDrawer(navView)
        }

        // BOTONES PRINCIPALES
        findViewById<Button>(R.id.btnDispositivos).setOnClickListener {
            startActivity(Intent(this, DispositivoActivity::class.java))
        }

        findViewById<Button>(R.id.btnModelos).setOnClickListener {
            startActivity(Intent(this, ModelosActivity::class.java))
        }

        findViewById<Button>(R.id.btnMarcas).setOnClickListener {
            startActivity(Intent(this, MarcaActivity::class.java))
        }

        findViewById<Button>(R.id.btnRegresar).setOnClickListener {
            finish() // Cierra esta Activity
        }

        // MENU LATERAL (Drawer)
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navCerrar -> finish() // Cierra la Activity al seleccionar "Cerrar"
                // Agrega aquí otros items si los tienes
            }
            drawer.closeDrawer(navView)
            true
        }
    }
}

