package com.example.fotovoltaico_esp32

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels

class AutomaticModePaneLedActivity : ComponentActivity() {
    private val sensorViewModel: SensorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_automatic_mode_pane_led)

        val btnBackToManual: Button = findViewById(R.id.btnBackToManual)

        btnBackToManual.setOnClickListener {
            if (Esp32Manager.sendData("MANUAL")) { // Comando alineado
                val intent = Intent(this, FuncionesEsp32Activity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT // Reutiliza la instancia existente
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error al activar modo manual", Toast.LENGTH_SHORT).show()
            }
        }
        //===============
        // Observar cambios en los datos del ViewModel
        sensorViewModel.sensorData.observe(this) { data ->
            Toast.makeText(this, "Received data: $data", Toast.LENGTH_SHORT).show()
            // Parsear los datos y actualizar gráficos
            parseAndUpdateUI(data)
        }
    }
    // Función para procesar los datos y actualizar la UI
    private fun parseAndUpdateUI(data: String) {
        try {
            val parts = data.split(",")
            val servoX = parts[0].split(":")[1].toInt()
            val servoY = parts[1].split(":")[1].toInt()
            val top = parts[2].split(":")[1].toInt()
            val bottom = parts[3].split(":")[1].toInt()
            val left = parts[4].split(":")[1].toInt()
            val right = parts[5].split(":")[1].toInt()

            // Actualizar gráficos aquí (ej: brújula, radar)
            updateCompass(servoX)
            updateRadarChart(top, bottom, left, right)
        } catch (e: Exception) {
            Log.e("SensorData", "Error parsing data: ${e.message}")
            e.printStackTrace()
        }
    }
    //
    private fun updateCompass(servoX: Int) {
        // Lógica para mover la brújula según servoX
        // Obtener referencia a la flecha de la brújula
        val compassArrow = findViewById<ImageView>(R.id.compassArrow)

        // Rotar la flecha según la posición del servo X (0°-180° → N, E, S)
        // Mapeo: 0° = Norte, 90° = Este, 180° = Sur
        compassArrow.rotation = servoX.toFloat()

        // Opcional: Animación suave
        compassArrow.animate().rotation(servoX.toFloat()).setDuration(200).start()
    }

    private fun updateRadarChart(top: Int, bottom: Int, left: Int, right: Int) {
        // Lógica para actualizar el gráfico de radar

    }
}