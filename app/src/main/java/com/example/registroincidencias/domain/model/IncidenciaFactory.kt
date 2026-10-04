package com.example.registroincidencias.domain.model

object IncidenciaFactory {
    fun desdeFormulario(
        titulo: String,
        descripcion: String,
        prioridad: Prioridad
    ): Incidencia = Incidencia(
        titulo = titulo,
        descripcion = descripcion,
        prioridad = prioridad
    )
}
