package com.example.fotovoltaico_esp32

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val esp32Name = "ESP32_ROOT" // Nombre del ESP32
    private val bluetoothPermissions = arrayOf(
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.BLUETOOTH_SCAN,
    )
    private val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private val requestCodePermissions = 1
    private val requestCodeLocationPermissions = 2

    @SuppressLint("MissingPermission", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnConnect: Button = findViewById(R.id.btnConnect)
        val tvStatus: TextView = findViewById(R.id.tvStatus)
        //Manejar el botón en MainActivity:
        val btnGetLocationAndTime: Button = findViewById(R.id.btnGetLocationAndTime)

        checkAndRequestPermissions()

        btnConnect.setOnClickListener {
            if (bluetoothAdapter == null) {
                tvStatus.text = "Bluetooth no disponible en este dispositivo"
                return@setOnClickListener
            }

            if (!bluetoothAdapter.isEnabled) {
                tvStatus.text = "Bluetooth no está activado"
                return@setOnClickListener
            }

            val device = findDeviceByName(esp32Name)
            if (device != null) {
                tvStatus.text = "Conectando a ${device.name}..."
                if (Esp32Manager.connectToDevice(this,device)) {
                    tvStatus.text = "Conexión exitosa con ${device.name}"
                    //
                } else {
                    tvStatus.text = "Error al conectar con ${device.name}"
                }
            } else {
                tvStatus.text = "Dispositivo $esp32Name no encontrado"
            }
        }
        //Manejar el botón en MainActivity:
        btnGetLocationAndTime.setOnClickListener {
            getCurrentLocation { latitude, longitude ->
                val time = getCurrentTime()
                showStatusMessage("Latitud: $latitude\nLongitud: $longitude\nHora: $time")

                // Crear Intent y pasar datos y pasar a otra ACTIVIDAD
                val intent = Intent(this, FuncionesEsp32Activity::class.java)
                intent.putExtra("LATITUDE", latitude.toString())
                intent.putExtra("LONGITUDE", longitude.toString())
                intent.putExtra("TIME", time)
                startActivity(intent)
            }
        }
        //
    }

    private fun checkAndRequestPermissions() {
        val missingPermissions = bluetoothPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (missingPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, missingPermissions.toTypedArray(), requestCodePermissions)
        }
    }

    @SuppressLint("MissingPermission")
    private fun findDeviceByName(name: String): BluetoothDevice? {
        val pairedDevices = bluetoothAdapter?.bondedDevices
        pairedDevices?.forEach { device ->
            if (device.name == name) {
                return device
            }
        }
        return null
    }
    //Pedir permisos para la localizacion del telefono
    //Método para pedir permisos de ubicación
    private fun checkAndRequestLocationPermissions() {
        val missingPermissions = locationPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (missingPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, missingPermissions.toTypedArray(), requestCodeLocationPermissions)
        }
    }
    //Método para obtener la ubicación actual
    private fun getCurrentLocation(onLocationReady: (latitude: Double, longitude: Double) -> Unit) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (locationPermissions.any {
                ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
            }
        ) {
            checkAndRequestLocationPermissions()
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                onLocationReady(location.latitude, location.longitude)
            } else {
                showStatusMessage("No se pudo obtener la ubicación actual")
            }
        }
    }
    //Método para obtener la hora actual
    private fun getCurrentTime(): String {
        val currentTime = Calendar.getInstance().time
        val format = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return format.format(currentTime)
    }
    private fun showStatusMessage(message: String) {
        val tvStatus: TextView = findViewById(R.id.tvStatus)
        tvStatus.text = message
    }

}


