package archtree.helper

import android.widget.Toast
import archknife.context.ContextProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToastHelper
@Inject constructor(private val contextProvider: ContextProvider,
                    private val appExecutor: AppExecutor) {

    fun show(messageRes: Int, duration: Int = Toast.LENGTH_SHORT) {
        appExecutor.mainThread.execute {
            contextProvider.activityContext?.run {
                Toast.makeText(this, messageRes, duration).show()
            }
        }
    }
}