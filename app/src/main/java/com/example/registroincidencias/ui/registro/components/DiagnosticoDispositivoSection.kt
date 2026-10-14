package com.example.registroincidencias.ui.registro.components

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.registroincidencias.domain.model.AcelerometroInterpreter
import com.example.registroincidencias.domain.model.EstadoDiagnostico

@Composable
fun DiagnosticoDispositivoSection(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var estado by remember { mutableStateOf<EstadoDiagnostico>(EstadoDiagnostico.SinLectura) }

    DisposableEffect(lifecycleOwner, context) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val mainHandler = Handler(Looper.getMainLooper())
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val frase = AcelerometroInterpreter.interpretar(
                    x = event.values[0],
                    y = event.values[1],
                    z = event.values[2]
                )
                mainHandler.post {
                    estado = EstadoDiagnostico.ConLectura(frase)
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
        }

        fun registrar() {
            if (sensor == null) {
                estado = EstadoDiagnostico.NoDisponible
                return
            }
            val registrado = sensorManager.registerListener(
                listener,
                sensor,
                SensorManager.SENSOR_DELAY_UI
            )
            if (!registrado) {
                estado = EstadoDiagnostico.NoDisponible
            }
        }

        fun liberar() {
            sensorManager.unregisterListener(listener)
        }

        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> registrar()
                Lifecycle.Event.ON_PAUSE -> liberar()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            registrar()
        }
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            liberar()
        }
    }

    DiagnosticoDispositivoContent(estado = estado, modifier = modifier)
}

@Composable
fun DiagnosticoDispositivoContent(
    estado: EstadoDiagnostico,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Diagnóstico del dispositivo",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Acelerómetro",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
            Text(
                text = estado.mensaje,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = estado.detalle,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
