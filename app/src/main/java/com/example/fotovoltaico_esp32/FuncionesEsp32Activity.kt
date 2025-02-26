package com.example.fotovoltaico_esp32

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalTime
import com.example.fotovoltaico_esp32.JoystickView


class FuncionesEsp32Activity : ComponentActivity() {
    private lateinit var textViewSensorData: TextView
    private var listeningJob: Job? = null
    //Para pasar los datos  a otra actividad hago esta variable
    private val sensorViewModel: SensorViewModel by viewModels() // Inicializa el ViewModel
    //Variables para dar fucnion del Joystick
    private lateinit var joystickView: JoystickView // Vista personalizada para el joystick
    private var horizontalAngle: Int = 90 // Ángulo inicial horizontal (centro)
    private var verticalAngle: Int = 90   // Ángulo inicial vertical (centro)

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funciones_esp32)

        val btnLedOn: Button = findViewById(R.id.btnLedOn)
        val btnLedOff: Button = findViewById(R.id.btnLedOff)
        val latitude = intent.getStringExtra("LATITUDE")
        val longitude = intent.getStringExtra("LONGITUDE")
        val time = intent.getStringExtra("TIME")
        textViewSensorData = findViewById(R.id.textViewSensorData)
        val btnSendServoData: Button = findViewById(R.id.btnSendServoData)

        if (!Esp32Manager.isConnected()) {
            Toast.makeText(this, "No hay conexión con el ESP32", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        //===========================Mandar a Encerder el Led del esp32
        btnLedOn.setOnClickListener {
            if (Esp32Manager.sendData("LED_ON")) {
                Toast.makeText(this, "Comando enviado: LED_ON", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al enviar comando", Toast.LENGTH_SHORT).show()
            }
        }
        //============================Mnadar a Apagar el LED del esp32
        btnLedOff.setOnClickListener {
            if (Esp32Manager.sendData("LED_OFF")) {
                Toast.makeText(this, "Comando enviado: LED_OFF", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al enviar comando", Toast.LENGTH_SHORT).show()
            }
        }
        // Puedes usar estos datos en tu lógica
        val tvData: TextView = findViewById(R.id.textViewUbicacion)
        tvData.text = "Latitud: $latitude\nLongitud: $longitude\nHora: $time"
        //BOTON en la pantalla para ir a modo automatico para que gire el panel por la luz
        //=====================================================Mnada a modo automatico y va a otra actividad
        btnSendServoData.setOnClickListener {
            if (Esp32Manager.sendData("AUTO")) { // Comando alineado con Arduino
                val intent = Intent(this, AutomaticModePaneLedActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT // Reutiliza la actividad si existe
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error al activar modo automático", Toast.LENGTH_SHORT).show()
            }
        }
        //========================
        // Inicializar el joystick
        joystickView = findViewById(R.id.joystickView)
        joystickView.setOnJoystickMoveListener { xPercent, yPercent ->
            // Actualizar ángulos según el movimiento del joystick
            calculateServoAngles(xPercent, yPercent)

            // Enviar comandos al ESP32
            sendServoCommands(horizontalAngle, verticalAngle)
        }
        //=====================
        startListeningForData()
    }
    //========================Funciones para mandar los mensajes al ESP32
    override fun onDestroy() {
        super.onDestroy()
        listeningJob?.cancel()
    }

    private fun startListeningForData() {
        listeningJob = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                val receivedData = Esp32Manager.receiveData()
                if (receivedData != null) {
                    // Actualizar el ViewModel con los nuevos datos
                    sensorViewModel.updateSensorData(receivedData)
                    withContext(Dispatchers.Main) {
                        textViewSensorData.text = receivedData

                    }
                }
            }
        }
    }
    //===================================================================Funciones necesarias para el joystick
    // Calcula los ángulos para los servos según la posición del joystick
    private fun calculateServoAngles(xPercent: Float, yPercent: Float) {
        // Convertir porcentaje (-1 a 1) a grados (0 a 180)
        horizontalAngle = ((xPercent + 1) * 90).toInt() // Mapea de -1..1 a 0..180
        verticalAngle = ((1 - yPercent) * 90).toInt()   // Invertir Y para alinearlo con la pantalla
    }

    // Enviar comandos al ESP32
    // Enviar comandos agrupados al ESP32
    private fun sendServoCommands(horizontal: Int, vertical: Int) {
        val command = "SERVO:$horizontal,$vertical\n" // Comando combinado para H y V
        val commandSent = Esp32Manager.sendData(command)

        if (commandSent) {
            Toast.makeText(
                this,
                "Comando enviado: H:$horizontal° V:$vertical°",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(this, "Error al enviar comandos", Toast.LENGTH_SHORT).show()
        }
    }
}
