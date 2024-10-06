package com.example.navegacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactForm(onRegister: (Contact) -> Unit, onShowList: () -> Unit) {
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    var apellido by remember { mutableStateOf(TextFieldValue("")) }
    var telefono by remember { mutableStateOf(TextFieldValue("")) }
    var hobbie by remember { mutableStateOf(TextFieldValue("")) }
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar(snackbarMessage)
            delay(2000) // Espera de 2 segundos
            showSnackbar = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        TextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Campo de Teléfono con validación para solo números
        TextField(
            value = telefono,
            onValueChange = {
                // Validamos que solo se ingresen números
                if (it.text.all { char -> char.isDigit() }) {
                    telefono = it
                }
            },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            // Teclado numérico
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = hobbie,
            onValueChange = { hobbie = it },
            label = { Text("Hobbie") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                // Validación de campos y registro
                if (nombre.text.isNotBlank() && apellido.text.isNotBlank() && telefono.text.isNotBlank() && hobbie.text.isNotBlank()) {
                    // Crear una nueva instancia de Contact con los parámetros nombrados
                    val contact = Contact(
                        firstName = nombre.text,
                        lastName = apellido.text,
                        phone = telefono.text,
                        hobby = hobbie.text
                    )
                    onRegister(contact)
                    // Limpiar los campos después de registrar
                    nombre = TextFieldValue("")
                    apellido = TextFieldValue("")
                    telefono = TextFieldValue("")
                    hobbie = TextFieldValue("")
                    snackbarMessage = "Contacto registrado correctamente"
                } else {
                    snackbarMessage = "Por favor, complete todos los campos"
                }
                showSnackbar = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }

        OutlinedButton(
            onClick = onShowList,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mostrar Contactos")
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}
