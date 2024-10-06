package com.example.navegacion

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp



@Composable
fun AppScreen(
    contacts: List<Contact>,
    onRegister: (Contact) -> Unit,
    onShowList: () -> Unit
) {
    Column {
        // Formulario para registrar un nuevo contacto
        ContactForm(onRegister = onRegister)

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para mostrar la lista de contactos
        Button(onClick = onShowList) {
            Text("Mostrar contactos")
        }
    }
}

@Composable
fun ContactForm(onRegister: (Contact) -> Unit) {
    // Aquí deberías incluir tu formulario para registrar contactos.
    // Por ejemplo, puedes utilizar los campos que definiste anteriormente.
}
