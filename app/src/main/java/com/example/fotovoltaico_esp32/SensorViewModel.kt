package com.example.fotovoltaico_esp32

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SensorViewModel : ViewModel(){
    // LiveData para almacenar los datos del ESP32
    private val _sensorData = MutableLiveData<String>()
    val sensorData: LiveData<String> get() = _sensorData

    // Función para actualizar los datos desde cualquier parte de la app
    fun updateSensorData(newData: String) {
        Log.d("SensorViewModel", "Updating sensor data: $newData")
        _sensorData.postValue(newData) // Actualiza en el hilo principal
    }
}