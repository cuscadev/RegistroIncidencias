package com.example.registroincidencias.domain.usecase

import com.example.registroincidencias.domain.model.Incidencia
import com.example.registroincidencias.domain.model.IncidenciaFactory
import com.example.registroincidencias.domain.model.Prioridad
import com.example.registroincidencias.domain.model.ReporteFeedback

/** Revisa el formulario y decide qué mensaje mostrar. Por ahora no guarda en BD. */
class CrearReporteUseCase {
    operator fun invoke(
        titulo: String,
        descripcion: String,
        prioridad: Prioridad
    ): ReporteFeedback {
        val incidencia: Incidencia = IncidenciaFactory.desdeFormulario(
            titulo = titulo,
            descripcion = descripcion,
            prioridad = prioridad
        )
        return if (incidencia.esValida()) {
            ReporteFeedback.Preparado(incidencia)
        } else {
            ReporteFeedback.TituloRequerido
        }
    }
}
