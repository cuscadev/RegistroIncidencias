# RegistroIncidencias

App Android en Kotlin (Jetpack Compose) para registrar incidencias de forma rápida.

- **Asignatura:** Técnicas de Producción Industrial de Software I (UTEC, ciclo 02-2026)
- **Sección:** 01
- **Docente:** Alvin Javier Portillo Tiliano
- **Estudiante:** Jonathan Alexander Hernández Martínez · Carné 29-0523-2021
- **Repositorio:** https://github.com/cuscadev/RegistroIncidencias

## Necesidad

En muchos entornos (oficina, laboratorio, planta o campus) las fallas, daños o situaciones
irregulares se reportan de forma informal (chat, papel o de memoria). Eso dificulta dar
seguimiento, perder detalle del problema y retrasar la atención. **RegistroIncidencias**
busca ofrecer una pantalla simple en el teléfono para capturar al menos un título y una
descripción breve, con confirmación visible al preparar el reporte.

## Usuario principal

Personal operativo, estudiante o colaborador que detecta una incidencia y necesita
dejar constancia inmediata desde el móvil, sin depender aún de un sistema completo
de tickets o base de datos.

## Requerimientos iniciales

### Funcionales
1. El usuario puede escribir el **título** de la incidencia.
2. El usuario puede escribir una **descripción** breve.
3. El usuario puede elegir la **prioridad** (Baja, Media o Alta) tocando una opción.
4. Al pulsar **Crear reporte**, la app valida y muestra un mensaje de éxito o de error
   (por ahora simulado; sin guardado en base de datos). El mensaje incluye la prioridad elegida.

### De interfaz / uso
1. Campos claros y etiquetados (Título y Descripción) en una sola pantalla.
2. El título abre el teclado de texto con mayúscula inicial y la acción **Siguiente**; la descripción usa la acción **Listo**.
3. Botón principal visible y relacionado con la acción de registrar/preparar el reporte.
4. Opciones de prioridad con área táctil amplia; la elegida cambia de color y se confirma en un texto.
5. Retroalimentación visible en pantalla: estado vacío, error (título requerido) o éxito.

## Avance actual (pantalla inicial)

La pantalla usa Compose con estado (`remember` / `mutableStateOf`):

- Encabezado con título e instrucción
- Campos de título y descripción con teclado contextual
- Selección táctil de prioridad (Baja, Media, Alta)
- Botón **Crear reporte**
- Mensaje: `Aún no hay reporte creado`, aviso si falta el título, o confirmación al preparar el reporte (incluye la prioridad)

Todavía **no** se persisten datos en base de datos (fuera del alcance de este avance).

## Avance semana 10 (teclado y pantalla táctil)

- **Teclado:** el campo Título usa `KeyboardType.Text`, `KeyboardCapitalization.Sentences` e `ImeAction.Next`, que pasa el foco a Descripción. Descripción usa la misma capitalización e `ImeAction.Done`, que cierra el teclado.
- **Toque:** la prioridad se elige con `Card(onClick)`, un componente de alto nivel. No hace falta `pointerInput` porque el gesto es un toque simple.
- **Retroalimentación:** la tarjeta elegida se resalta y el texto `Prioridad seleccionada: …` cambia al instante. Al crear el reporte, el mensaje también muestra esa prioridad.
- **Dificultad resuelta:** el teclado genérico no seguía el flujo del formulario (no avanzaba al siguiente campo) y no había forma de indicar urgencia sin escribirla en la descripción.

## Avance semana 12 (diagnóstico del dispositivo)

Sección **Diagnóstico del dispositivo** al final de la pantalla. Usa el acelerómetro:

- Comprueba si el sensor existe. Si no está, muestra `Sin lectura disponible` y explica que el equipo no tiene acelerómetro.
- Se registra en `ON_RESUME` y se libera en `ON_PAUSE`, para no seguir escuchando con la app en segundo plano.
- La lectura se traduce a una frase: `Movimiento detectado` o `Dispositivo en reposo`. No se muestran los ejes sueltos.
- En el emulador el acelerómetro es virtual: en reposo suele verse gravedad fija. El movimiento se prueba cambiando el sensor virtual. En un teléfono físico se comprobaría caminando y al bloquear la pantalla.

### Estructura del código

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

## Cómo ejecutar

1. Abrir el proyecto en Android Studio.
2. Esperar a que Gradle sincronice.
3. Ejecutar en emulador o dispositivo físico.
4. Escribir título (y opcionalmente descripción), tocar una prioridad y pulsar **Crear reporte**. Revisar el texto de prioridad y el mensaje del reporte.
5. Bajar hasta **Diagnóstico del dispositivo** y comprobar la frase del acelerómetro.

## Commits relevantes

- Inicialización del proyecto Android
- Pantalla de registro con estado en Compose
- Mejora del mensaje del reporte y limpieza del formulario
- Teclado contextual y selección táctil de prioridad (semana 10)
- Diagnóstico del dispositivo con el acelerómetro (semana 12)
