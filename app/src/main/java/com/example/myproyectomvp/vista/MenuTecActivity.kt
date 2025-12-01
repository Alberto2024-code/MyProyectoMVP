package com.example.myproyectomvp.vista

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myproyectomvp.Contrato.MenuTecContrato
import com.example.myproyectomvp.Modelo.ApiService
import com.example.myproyectomvp.Modelo.MenuTecUser
import com.example.myproyectomvp.Modelo.Retrofit
import com.example.myproyectomvp.Presentador.MenuTecPresenter
import com.example.myproyectomvp.R
import com.google.android.material.navigation.NavigationView

class MenuTecActivity : AppCompatActivity(), MenuTecContrato.View {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var btnHamburguesa: ImageButton
    private lateinit var txtTitulo: TextView

    private lateinit var presenter: MenuTecContrato.Presenter
    private lateinit var apiService: ApiService // Instancia de tu API ya inicializada

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menutec)

        // Inicializar vistas
        drawerLayout = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.navView)
        btnHamburguesa = findViewById(R.id.btnHamburguesa)
        txtTitulo = findViewById(R.id.txtTitulo)

        // Aquí asigna tu ApiService ya creado en tu proyecto
        presenter = MenuTecPresenter(this, Retrofit.api)

        presenter.loadUser()

        // Abrir / Cerrar menú hamburguesa
        btnHamburguesa.setOnClickListener {
            if (drawerLayout.isDrawerOpen(navView)) {
                drawerLayout.closeDrawer(navView)
            } else {
                drawerLayout.openDrawer(navView)
            }
        }

        // Manejo de clics del menú lateral
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navMapa -> presenter.openMapa()

                R.id.navRegistros ->{
                    val intent = Intent(this, RegistrosTecActivity ::class.java)
                    startActivity(intent)
                }


                R.id.navCerrar -> presenter.logout()
            }
            drawerLayout.closeDrawer(navView)
            true
        }
    }

    override fun showUser(user: MenuTecUser) {
        txtTitulo.text = "Bienvenido, ${user.nombre}"
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
