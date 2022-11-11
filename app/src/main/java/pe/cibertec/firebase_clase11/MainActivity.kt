package pe.cibertec.firebase_clase11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pe.cibertec.firebase_clase11.model.Persona
import pe.cibertec.firebase_clase11.network.PersonaDataSource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

/*
        // LEER
        db.collection(collectionName)
            .get()
            .addOnSuccessListener { query ->
                for (doc in query) {
                    Log.i("DATA","ID: ${doc.id} => ${doc.data}")
                }
            }
            .addOnFailureListener { e ->
                Log.e("ERROR","No se pudo leer $e")
            }
*/
/*
        // INSERTAR
        val persona = hashMapOf(
            "nombre" to "Ramiro",
            "apellido" to "Gomez",
            "edad" to 30
        )

        db.collection(collectionName).add(persona).addOnSuccessListener { docref ->
            Log.i("EXITO","Se registro la nueva persona con id ${docref.id}")
        }.addOnFailureListener { e ->
            Log.e("ERROR","No se pudo registrar / $e")
        }
*/
    }
}