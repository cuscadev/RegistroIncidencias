package com.example.registroincidencias.domain.model

import kotlin.math.abs
import kotlin.math.sqrt

/** Traduce la lectura del acelerómetro a una frase que el usuario puede entender. */
object AcelerometroInterpreter {
    private const val GRAVEDAD = 9.81f
    private const val UMBRAL_MOVIMIENTO = 2f

    fun interpretar(x: Float, y: Float, z: Float): String {
        val magnitud = sqrt(x * x + y * y + z * z)
        val desviacion = abs(magnitud - GRAVEDAD)
        return if (desviacion >= UMBRAL_MOVIMIENTO) {
            "Movimiento detectado"
        } else {
            "Dispositivo en reposo"
        }
    }
}
