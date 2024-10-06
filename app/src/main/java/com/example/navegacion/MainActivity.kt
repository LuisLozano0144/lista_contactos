import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.navegacion.AppScreen
import com.example.navegacion.Contact
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var db: ContactDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar la base de datos
        db = ContactDatabase.getDatabase(this)

        setContent {
            var contacts by remember { mutableStateOf(listOf<Contact>()) }

            // Scope para ejecutar coroutines dentro de la composición
            val coroutineScope = rememberCoroutineScope()

            // Recuperar contactos al iniciar la pantalla
            LaunchedEffect(Unit) {
                contacts = db.contactDao().getAllContacts()
            }

            // Interfaz principal de la app
            AppScreen(
                contacts = contacts,
                onRegister = { contact ->
                    coroutineScope.launch {
                        db.contactDao().insertContact(contact)
                        contacts = db.contactDao().getAllContacts() // Actualizamos la lista
                    }
                },
                onShowList = {
                    // Lógica para mostrar la lista
                }
            )
        }
    }
}
