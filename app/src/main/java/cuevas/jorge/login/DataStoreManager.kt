package cuevas.jorge.login

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cuevas.jorge.login.data.models.Producto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension para instanciar DataStore
private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {
    // Para serializar los productos
    private val gson = Gson()

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val CARRITO_JSON = stringPreferencesKey("carrito_json")
    }

    // --- Logica de Login ---
    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[IS_LOGGED_IN] ?: false }

    suspend fun saveLoginStatus(isLoggedIn: Boolean) {
        context.dataStore.edit { it[IS_LOGGED_IN] = isLoggedIn }
    }

    // --- Logica de Carrito ---
    val carritoFlow: Flow<List<Producto>> = context.dataStore.data
        .map { preferences ->
            val json = preferences[CARRITO_JSON]
            if (json == null) emptyList()
            else {
                val tipo = object : TypeToken<List<Producto>>() {}.type
                gson.fromJson(json, tipo)
            }
        }

    suspend fun guardarCarrito(productos: List<Producto>) {
        val json = gson.toJson(productos)
        context.dataStore.edit { it[CARRITO_JSON] = json }
    }

    suspend fun clearAll() {
        context.dataStore.edit { it.clear() }
    }
}