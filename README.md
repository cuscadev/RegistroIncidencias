# RegistroIncidencias

App Android en Kotlin para registrar incidencias. Trabajo de la clase Técnicas de Producción Industrial de Software I (UTEC, ciclo 02-2026).

Repo: https://github.com/cuscadev/RegistroIncidencias

## Semana 6

La pantalla ya no es solo estática. Con Compose guardo lo que escribo en estado (`remember` / `mutableStateOf`) y al tocar **Crear reporte** sale un mensaje en pantalla. Todavía no hay base de datos.

Incluye:
- título e instrucción
- campos de título y descripción
- botón Crear reporte
- mensaje de confirmación o aviso si falta el título

Separé el código en carpetas para que sea más fácil de mantener:

```text
app/src/main/java/com/example/registroincidencias/
├── MainActivity.kt
├── domain/model/
├── domain/usecase/
└── ui/
    ├── theme/
    └── registro/
        ├── RegistroIncidenciaUiState.kt
        ├── RegistroIncidenciaScreen.kt
        └── components/
```

## Cómo correrla

1. Abrir el proyecto en Android Studio.
2. Esperar a que Gradle sincronice.
3. Correrla en emulador o celular.
4. Escribir algo, pulsar Crear reporte y revisar el mensaje.
