package com.example.registroincidencias.domain.model

/** Datos que el usuario escribe en el formulario. */
data class Incidencia(
    val titulo: String,
    val descripcion: String
) {
    val tituloNormalizado: String
        get() = titulo.trim()

    val descripcionNormalizada: String
        get() = descripcion.trim()

    fun esValida(): Boolean = tituloNormalizado.isNotEmpty()
}
