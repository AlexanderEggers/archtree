package archtree.helper

import archknife.context.ContextProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonHelper @Inject constructor(private val contextProvider: ContextProvider) {

    /**
     * Returns a string for the given resource.
     *
     * @param fileName name of the file which content should be converted into a string
     * @return a new string
     * @since 1.0.0
     */
    fun getJsonString(fileName: String): String? {
        return try {
            contextProvider.applicationContext?.assets?.open(fileName)?.bufferedReader()?.use { reader ->
                reader.readText()
            }
        } catch (ignore: Exception) {
            null
        }
    }
}