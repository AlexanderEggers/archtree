package archtree.helper

import android.support.annotation.WorkerThread
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonConverter
@Inject constructor(private val gson: Gson) {

    @WorkerThread
    fun convertJson(value: String, type: Type): JsonArray {
        return gson.fromJson(value, type)
    }
    
    @WorkerThread
    fun toJsonTree(value: List<*>): JsonArray {
        return gson.toJsonTree(value).asJsonArray
    }

    @WorkerThread
    fun toJson(value: Any): String {
        return gson.toJson(value)
    }
}