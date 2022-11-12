package pe.cibertec.firebase_clase11.repository

import pe.cibertec.firebase_clase11.model.PersonaCollection
import pe.cibertec.firebase_clase11.network.PersonaDataSource
import pe.cibertec.firebase_clase11.network.Respuesta

class PersonaRepository(private val dataSource: PersonaDataSource) {

    suspend fun listarTodos(): Respuesta<List<PersonaCollection>> {
        return dataSource.listarTodos()
    }

}