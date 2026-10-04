package com.example.registroincidencias.domain.model

/** Nivel que el usuario elige tocando una opción en el formulario. */
enum class Prioridad(val etiqueta: String) {
    BAJA("Baja"),
    MEDIA("Media"),
    ALTA("Alta")
}
