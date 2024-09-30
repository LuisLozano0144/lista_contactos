package com.example.navegacion

import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContactApp() {
    var contacts by remember { mutableStateOf(listOf<Contact>()) }
    var currentView by remember { mutableStateOf("form") }

    if (currentView  == "form") {
        ContactForm(
            onRegister = { contact ->
                contacts = contacts + contact
            },
            onShowList = {
                currentView = "list"
            }
        )
    } else {
        ContactList(contacts, onBack = { currentView = "form" })
    }
}

@Preview(showBackground = true)
@Composable
fun ContactAppPreview() {
    ContactApp()
}