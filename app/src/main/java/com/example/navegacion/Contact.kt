package com.example.navegacion

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Asegúrate de que sea Int
    val firstName: String,
    val lastName: String,
    val phone: String,
    val hobby: String
)
