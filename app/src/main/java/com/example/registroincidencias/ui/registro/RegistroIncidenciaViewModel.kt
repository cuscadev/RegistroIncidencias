package com.example.registroincidencias.ui.registro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.registroincidencias.domain.model.Prioridad

/**
 * Estado del formulario que debe sobrevivir a la recreación de la Activity
 * (por ejemplo al rotar). La UI solo avisa el evento.
 */
class RegistroIncidenciaViewModel : ViewModel() {
    var titulo by mutableStateOf("")
        private set

    var prioridad by mutableStateOf(Prioridad.MEDIA)
        private set

    fun actualizarTitulo(valor: String) {
        titulo = valor
    }

    fun seleccionarPrioridad(valor: Prioridad) {
        prioridad = valor
    }

    fun limpiarTitulo() {
        titulo = ""
    }
}
