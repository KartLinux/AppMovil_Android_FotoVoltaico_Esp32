package com.example.fotovoltaico_esp32
// Encargada de calcular los ángulos de azimut y elevación en base a latitud, longitud y hora.
object SolarCalculator {
    // Función para calcular el ángulo de azimut (horizontal)
    fun calculateAzimuth(latitude: Double, longitude: Double, time: Double): Double {
        return (longitude + time * 15) % 360 // Operación corregida y simplificada
    }

    // Función para calcular el ángulo de elevación (vertical)
    fun calculateElevation(latitude: Double, longitude: Double, time: Double): Double {
        return Math.abs(latitude - (time * 10) % 90) // Operación corregida
    }
}