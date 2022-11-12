package pe.cibertec.firebase_clase11.network

sealed class Respuesta<out T: Any> {
    data class exito<out T: Any>(val data: T): Respuesta<T>()
    data class error(val mensaje: String): Respuesta<Nothing>()
}
