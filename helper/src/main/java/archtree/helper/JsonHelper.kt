package archtree.helper

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonHelper @Inject constructor(private val context: Context) {

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