package archtree.helper

import com.google.gson.Gson
import com.google.gson.JsonArray
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonConverter
@Inject constructor(private val gson: Gson) {

    fun toJsonTree(value: List<*>): JsonArray {
        return gson.toJsonTree(value).asJsonArray
    }

    fun toJson(value: Any): String {
        return gson.toJson(value)
    }
}