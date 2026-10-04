package com.example.registroincidencias.ui.registro.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.registroincidencias.domain.model.Prioridad

@Composable
fun PrioridadSelector(
    prioridad: Prioridad,
    onPrioridadChange: (Prioridad) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Prioridad",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Medium
        )
        Prioridad.entries.forEach { opcion ->
            val seleccionada = opcion == prioridad
            Card(
                onClick = { onPrioridadChange(opcion) },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (seleccionada) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                ),
                border = BorderStroke(
                    width = if (seleccionada) 2.dp else 1.dp,
                    color = if (seleccionada) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.outline
                    }
                )
            ) {
                Text(
                    text = opcion.etiqueta,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                    fontWeight = if (seleccionada) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
        Text(
            text = "Prioridad seleccionada: ${prioridad.etiqueta}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
