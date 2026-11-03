package com.example.registroincidencias.ui.registro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.registroincidencias.domain.model.Prioridad
import com.example.registroincidencias.domain.model.ReporteFeedback
import com.example.registroincidencias.domain.usecase.CrearReporteUseCase
import com.example.registroincidencias.ui.registro.components.CrearReporteSection
import com.example.registroincidencias.ui.registro.components.DiagnosticoDispositivoSection
import com.example.registroincidencias.ui.registro.components.IncidenciaFormFields
import com.example.registroincidencias.ui.registro.components.PrioridadSelector
import com.example.registroincidencias.ui.registro.components.RegistroHeader
import com.example.registroincidencias.ui.theme.RegistroIncidenciasTheme

@Composable
fun RegistroIncidenciaScreen(
    crearReporteUseCase: CrearReporteUseCase = remember { CrearReporteUseCase() },
    modifier: Modifier = Modifier
) {
    var titulo by rememberSaveable { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var prioridad by remember { mutableStateOf(Prioridad.MEDIA) }
    var feedback by remember { mutableStateOf<ReporteFeedback>(ReporteFeedback.SinReporte) }

    val uiState = RegistroIncidenciaUiState(
        titulo = titulo,
        descripcion = descripcion,
        prioridad = prioridad,
        feedback = feedback
    )

    RegistroIncidenciaContent(
        uiState = uiState,
        onTituloChange = { titulo = it },
        onDescripcionChange = { descripcion = it },
        onPrioridadChange = { prioridad = it },
        onCrearReporte = {
            val result = crearReporteUseCase(
                titulo = titulo,
                descripcion = descripcion,
                prioridad = prioridad
            )
            if (result is ReporteFeedback.Preparado) {
                titulo = ""
                descripcion = ""
                feedback = result
            } else {
                feedback = result
            }
        },
        modifier = modifier
    )
}

@Composable
fun RegistroIncidenciaContent(
    uiState: RegistroIncidenciaUiState,
    onTituloChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    onPrioridadChange: (Prioridad) -> Unit,
    onCrearReporte: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RegistroHeader(
            titulo = "Registro de incidencia",
            instruccion = "Escribe el título y una descripción breve, elige la prioridad con un toque y pulsa Crear reporte."
        )
        IncidenciaFormFields(
            titulo = uiState.titulo,
            descripcion = uiState.descripcion,
            onTituloChange = onTituloChange,
            onDescripcionChange = onDescripcionChange
        )
        PrioridadSelector(
            prioridad = uiState.prioridad,
            onPrioridadChange = onPrioridadChange
        )
        CrearReporteSection(
            mensajeFeedback = uiState.mensajeFeedback,
            onCrearReporte = onCrearReporte
        )
        DiagnosticoDispositivoSection()
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistroIncidenciaScreenPreview() {
    RegistroIncidenciasTheme {
        RegistroIncidenciaScreen()
    }
}