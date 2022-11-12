package pe.cibertec.firebase_clase11.network

import com.google.firebase.firestore.FirebaseFirestore
import pe.cibertec.firebase_clase11.model.Persona
import pe.cibertec.firebase_clase11.model.PersonaCollection

class PersonaDataSource(private val  db: FirebaseFirestore) {

    private val collectionName = "Personas"

    suspend fun listarTodos(): Respuesta<List<PersonaCollection>> {
        val lista = mutableListOf<PersonaCollection>()
        return try {
            db.collection(collectionName)
                .get()
                .addOnSuccessListener { query ->
                    for (doc in query) {
                        val obj = doc.toObject(Persona::class.java)
                        val objCollection = PersonaCollection(doc.id, obj)
                        lista.add(objCollection)
                    }
                }
                .addOnFailureListener { it }
            Respuesta.exito(lista)
        } catch (e: Exception) {
            Respuesta.error("${e.toString()}")
        }
    }

}