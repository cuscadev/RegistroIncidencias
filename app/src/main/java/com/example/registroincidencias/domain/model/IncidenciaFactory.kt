package com.example.registroincidencias.domain.model

object IncidenciaFactory {
    fun desdeFormulario(titulo: String, descripcion: String): Incidencia =
        Incidencia(titulo = titulo, descripcion = descripcion)
}
