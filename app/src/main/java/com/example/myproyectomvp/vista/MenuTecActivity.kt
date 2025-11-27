package com.example.myproyectomvp.vista

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myproyectomvp.Contrato.MenuTecContrato
import com.example.myproyectomvp.Modelo.MenuTecUser
import com.example.myproyectomvp.Presentador.MenuTecPresenter
import com.example.myproyectomvp.R
import com.google.android.material.navigation.NavigationView

class MenuTecActivity  : AppCompatActivity(), MenuTecContrato.View {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var btnHamburguesa: ImageButton
    private lateinit var txtTitulo: TextView

    private lateinit var presenter: MenuTecContrato.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menutec) // tu layout DrawerLayout

        // Inicializar vistas
        drawerLayout = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.navView)
        btnHamburguesa = findViewById(R.id.btnHamburguesa)
        txtTitulo = findViewById(R.id.txtTitulo)

        // Inicializar Presentador
        presenter = MenuTecPresenter(this)

        // Cargar usuario
        presenter.loadUser()

        // Botón de hamburguesa abre/cierra el drawer
        btnHamburguesa.setOnClickListener {
            if (drawerLayout.isDrawerOpen(navView)) {
                drawerLayout.closeDrawer(navView)
            } else {
                drawerLayout.openDrawer(navView)
            }
        }

        // Opcional: manejar clicks del menú lateral
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navMapa -> presenter.openMapa()
                R.id.navOrden -> presenter.openOrden()
                R.id.navRegistros -> presenter.openRegistros()
                R.id.navView -> presenter.logout()
                R.id.navCerrar -> presenter.loadUser()
            }
            drawerLayout.closeDrawer(navView)
            true
        }
    }

    // Implementación de la vista
    override fun showUser(user: MenuTecUser) {
        txtTitulo.text = "Hola, ${user.nombre}"
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}