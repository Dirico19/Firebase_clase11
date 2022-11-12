package pe.cibertec.firebase_clase11.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pe.cibertec.firebase_clase11.model.PersonaCollection
import pe.cibertec.firebase_clase11.network.Respuesta
import pe.cibertec.firebase_clase11.repository.PersonaRepository

class PersonaViewModel(private val repository: PersonaRepository): ViewModel() {

    private var _lista = MutableLiveData<List<PersonaCollection>>()
    val lista: LiveData<List<PersonaCollection>> get() = _lista

    fun listarTodos() {
        viewModelScope.launch {
            when (val res = repository.listarTodos()) {
                is Respuesta.exito -> {
                    _lista.value = res.data
                }
                is Respuesta.error -> {

                }
            }
        }
    }

}