package com.example.registroincidencias

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.registroincidencias.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGuardar.setOnClickListener {
            val titulo = binding.editTextTitulo.text?.toString()?.trim().orEmpty()
            val descripcion = binding.editTextDescripcion.text?.toString()?.trim().orEmpty()

            if (titulo.isEmpty()) {
                binding.editTextTitulo.error = getString(R.string.error_titulo)
                return@setOnClickListener
            }

            if (descripcion.isEmpty()) {
                binding.editTextDescripcion.error = getString(R.string.error_descripcion)
                return@setOnClickListener
            }

            Toast.makeText(this, getString(R.string.incidencia_guardada), Toast.LENGTH_SHORT)
                .show()
            binding.editTextTitulo.text?.clear()
            binding.editTextDescripcion.text?.clear()
        }
    }
}
