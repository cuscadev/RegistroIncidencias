package com.example.registroincidencias.domain.model

/** Resultado de pulsar Crear reporte (éxito, falta título, o aún no hay nada). */
sealed class ReporteFeedback {
    data object SinReporte : ReporteFeedback()

    data class Preparado(val incidencia: Incidencia) : ReporteFeedback()

    data object TituloRequerido : ReporteFeedback()

    fun asMensaje(): String = when (this) {
        SinReporte -> "Aún no hay reporte creado"
        TituloRequerido -> "Escribe un título antes de crear el reporte"
        is Preparado -> {
            val titulo = incidencia.tituloNormalizado
            val descripcion = incidencia.descripcionNormalizada
            val prioridad = incidencia.prioridad.etiqueta
            if (descripcion.isEmpty()) {
                "Reporte preparado: $titulo\nPrioridad: $prioridad"
            } else {
                "Reporte preparado: $titulo\nPrioridad: $prioridad\nDescripción: $descripcion"
            }
        }
    }
}
