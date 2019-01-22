package archtree.helper

import androidx.core.app.ActivityCompat
import archknife.context.ContextProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionHelper @Inject constructor(private val contextProvider: ContextProvider) {

    fun requestPermissions(permissions: Array<String>, requestCode: Int) {
        contextProvider.activity?.run {
            ActivityCompat.requestPermissions(this, permissions, requestCode)
        }
    }
}