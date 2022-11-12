package pe.cibertec.firebase_clase11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pe.cibertec.firebase_clase11.databinding.ActivityMainBinding
import pe.cibertec.firebase_clase11.model.Persona
import pe.cibertec.firebase_clase11.model.PersonaCollection
import pe.cibertec.firebase_clase11.network.PersonaDataSource
import pe.cibertec.firebase_clase11.network.Respuesta
import pe.cibertec.firebase_clase11.viewmodel.PersonaViewModel
import pe.cibertec.firebase_clase11.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    val binding get() = _binding!!

    private val personaViewModel by viewModels<PersonaViewModel> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        when (val res = listar()){
            is Respuesta.exito -> {
                for (p in res.data.value!!) {
                    Log.i("Personas", "${p.id}: ${p.data.nombre}")
                }
                Log.i("Personas", "---------------")
            }
            is Respuesta.error -> {
                Log.i("Personas", "${res.mensaje}")
            }
        }


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

    fun listar(): Respuesta<MutableLiveData<MutableList<PersonaCollection>>> {
        val mutableData = MutableLiveData<MutableList<PersonaCollection>>()
        return try {
            Firebase.firestore.collection("Personas")
                .get()
                .addOnSuccessListener { query ->
                    val lista = mutableListOf<PersonaCollection>()
                    for (doc in query) {
                        val obj = Persona("aaaaaa","aaaaaa",10)
                        val objCollection = PersonaCollection(doc.id, obj)
                        lista.add(objCollection)
                    }
                    mutableData.value = lista
                }
                .addOnFailureListener { throw it }
            Respuesta.exito(mutableData)
        } catch (e: Exception) {
            Respuesta.error("${e.toString()}")
        }
    }
}