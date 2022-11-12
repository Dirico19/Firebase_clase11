package pe.cibertec.firebase_clase11.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pe.cibertec.firebase_clase11.network.PersonaDataSource
import pe.cibertec.firebase_clase11.repository.PersonaRepository

class ViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonaViewModel::class.java)) {
            return PersonaViewModel(PersonaRepository(PersonaDataSource(Firebase.firestore))) as T
        }

        throw IllegalArgumentException("No corresponde a ningún viewmodel")
    }

}