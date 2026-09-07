package com.example.registroincidencias.ui.registro

import com.example.registroincidencias.domain.model.ReporteFeedback

/** Valores que la pantalla va actualizando mientras el usuario escribe. */
data class RegistroIncidenciaUiState(
    val titulo: String = "",
    val descripcion: String = "",
    val feedback: ReporteFeedback = ReporteFeedback.SinReporte
) {
    val mensajeFeedback: String
        get() = feedback.asMensaje()
}
