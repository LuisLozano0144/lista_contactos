package com.example.navegacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ContactList(contacts: List<Contact>, onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            Text(
                text = "Lista de Contactos",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                color = MaterialTheme.colorScheme.primary
            )

            if (contacts.isEmpty()) {
                Text(
                    text = "No ha creado contactos",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f) // Ocupa todo el espacio disponible
                ) {
                    items(contacts.size) { index ->
                        val contact = contacts[index]
                        ContactItem(contact)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }

        // Botón "Volver" centrado en la parte inferior de la pantalla
        OutlinedButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Volver")
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${contact.firstName} ${contact.lastName}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Teléfono: ${contact.phone}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Text(
                text = "Hobbie: ${contact.hobby}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}
