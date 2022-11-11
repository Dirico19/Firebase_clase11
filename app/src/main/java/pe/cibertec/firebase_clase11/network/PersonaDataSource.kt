package pe.cibertec.firebase_clase11.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import pe.cibertec.firebase_clase11.model.Persona
import pe.cibertec.firebase_clase11.model.PersonaCollection
import java.lang.reflect.InvocationHandler

class PersonaDataSource(private val  db: FirebaseFirestore) {

    private val collectionName = "Personas"

    fun listarTodos(completationHandler: (List<PersonaCollection>) -> Unit) {
        db.collection(collectionName)
            .get()
            .addOnSuccessListener { query ->
                val lista = mutableListOf<PersonaCollection>()
                for (doc in query) {
                    val obj = doc.toObject(Persona::class.java)
                    val objCollection = PersonaCollection(doc.id, obj)
                    lista.add(objCollection)
                }
                completationHandler(lista)
            }
            .addOnFailureListener { e ->
                 Log.e("ERROR", e.toString())
            }
    }

}