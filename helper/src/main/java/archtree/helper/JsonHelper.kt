package archtree.helper

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import com.google.gson.Gson
import com.google.gson.JsonArray
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonHelper
@Inject constructor(private val context: Context,
                    private val gson: Gson) {

    @WorkerThread
    fun <T> convertJson(value: String, type: Type): T? {
        return try {
            gson.fromJson(value, type)
        } catch (e: Exception) {
            null
        }
    }

    @WorkerThread
    fun toJsonTree(value: List<*>): JsonArray? {
        return try {
            gson.toJsonTree(value).asJsonArray
        } catch (e: Exception) {
            null
        }
    }

    @WorkerThread
    fun toJson(value: Any): String {
        return gson.toJson(value)
    }

    /**
     * Returns a string for the given resource.
     *
     * @param fileName name of the file which content should be converted into a string
     * @return a new string
     * @since 1.0.0
     */
    fun getJsonStringFromFile(fileName: String): String? {
        return try {
            context.assets?.open(fileName)?.bufferedReader()?.use { reader ->
                reader.readText()
            }
        } catch (ignore: Exception) {
            null
        }
    }
}