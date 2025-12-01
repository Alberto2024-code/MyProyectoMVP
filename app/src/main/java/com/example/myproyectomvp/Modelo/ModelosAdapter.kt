
package com.example.myproyectomvp.Adaptadores

import android.content.Context
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.myproyectomvp.Modelo.Modelo

class ModelosAdapter(
    private val context: Context,
    private val tableLayout: TableLayout
) {

    fun cargarTabla(modelos: List<Modelo>) {

        if (tableLayout.childCount > 1) {
            tableLayout.removeViews(1, tableLayout.childCount - 1)
        }

        for (modelo in modelos) {

            val fila = TableRow(context)

            // --- ID ---
            val tvId = TextView(context).apply {
                text = modelo.idModelo.toString()
                setPadding(10, 10, 10, 10)
            }

            // --- Modelo ---
            val tvModelo = TextView(context).apply {
                text = modelo.nombreModelo
                setPadding(10, 10, 10, 10)
            }

            // --- Marca ---
            val tvMarca = TextView(context).apply {
                text = modelo.nombreMarca
                setPadding(10, 10, 10, 10)
            }

            // Agregar las vistas a la fila
            fila.addView(tvId)
            fila.addView(tvModelo)
            fila.addView(tvMarca)

            // Agregar la fila a la tabla
            tableLayout.addView(fila)
        }
    }
}

