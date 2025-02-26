package com.example.fotovoltaico_esp32
import android.Manifest
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.UUID

object Esp32Manager {
    private const val UUID_STRING = "00001101-0000-1000-8000-00805F9B34FB"
    private var bluetoothSocket: BluetoothSocket? = null

    // Conectar al dispositivo Bluetooth
    fun connectToDevice(context: Context, device: BluetoothDevice): Boolean {
        return try {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Puedes solicitar permisos aquí si es necesario
                return false
            }

            bluetoothSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(UUID_STRING))
            bluetoothSocket?.connect()
            true // Conexión exitosa
        } catch (e: IOException) {
            e.printStackTrace()
            bluetoothSocket?.close()
            bluetoothSocket = null
            false // Error al conectar
        }
    }


    // Cerrar la conexión Bluetooth
    fun closeConnection() {
        try {
            bluetoothSocket?.close()
            bluetoothSocket = null
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Enviar datos al ESP32
    fun sendData(data: String): Boolean {
        return try {
            bluetoothSocket?.outputStream?.write(data.toByteArray())
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    // Leer datos del ESP32
    fun receiveData(): String? {
        return try {
            // Asegúrate de que bluetoothSocket no sea nulo
            val inputStream = bluetoothSocket?.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))

            // Leer una línea completa de texto
            val data = reader.readLine()
            data // Retorna la línea leída

        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    // Verificar si está conectado
    fun isConnected(): Boolean {
        return bluetoothSocket != null && bluetoothSocket!!.isConnected
    }
}
