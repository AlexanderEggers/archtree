package archtree.helper

import androidx.annotation.WorkerThread
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class JsonConverter
@Inject constructor(private val gson: Gson) {

    @WorkerThread
    open fun <T> convertJson(value: String, type: Type): T? {
        return try {
            gson.fromJson(value, type)
        } catch (e: Exception) {
            null
        }
    }
    
    @WorkerThread
    open fun toJsonTree(value: List<*>): JsonArray? {
        return try {
            gson.toJsonTree(value).asJsonArray
        } catch (e: Exception) {
            Log.wtf(JsonConverter::class.java.name, "Given JsonElement is not a JsonArray.")
            null
        }
    }

    @WorkerThread
    open fun toJson(value: Any): String {
        return gson.toJson(value)
    }
}