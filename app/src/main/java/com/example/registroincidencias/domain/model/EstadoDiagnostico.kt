package com.example.registroincidencias.domain.model

/** Lo que la sección de diagnóstico muestra, sin números sueltos. */
sealed interface EstadoDiagnostico {
    val mensaje: String
    val detalle: String

    data object SinLectura : EstadoDiagnostico {
        override val mensaje = "Sin lectura disponible"
        override val detalle = "Aún no llega una lectura del acelerómetro."
    }

    data object NoDisponible : EstadoDiagnostico {
        override val mensaje = "Sin lectura disponible"
        override val detalle = "Este equipo no tiene acelerómetro."
    }

    data class ConLectura(val interpretacion: String) : EstadoDiagnostico {
        override val mensaje = interpretacion
        override val detalle = when (interpretacion) {
            "Movimiento detectado" -> "El teléfono se está moviendo."
            else -> "El teléfono está quieto."
        }
    }
}
