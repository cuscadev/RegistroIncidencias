package com.example.registroincidencias.ui.registro

import com.example.registroincidencias.domain.model.Prioridad
import com.example.registroincidencias.domain.model.ReporteFeedback

/** Valores que la pantalla va actualizando mientras el usuario escribe o toca. */
data class RegistroIncidenciaUiState(
    val titulo: String = "",
    val descripcion: String = "",
    val prioridad: Prioridad = Prioridad.MEDIA,
    val feedback: ReporteFeedback = ReporteFeedback.SinReporte
) {
    val mensajeFeedback: String
        get() = feedback.asMensaje()
}
