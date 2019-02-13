package archtree.helper

import android.content.Context
import android.view.accessibility.AccessibilityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessibilityHelper
@Inject constructor(private val context: Context) {

    fun observeForAccessibilityChanges(): LiveData<Boolean> {
        val am = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager?
        val source = MutableLiveData<Boolean>()

        am?.addAccessibilityStateChangeListener { source.value = it }
        source.value = am?.isEnabled ?: false

        return source
    }

    fun isAccessiblityEnabled(): Boolean {
        val am = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager?
        return am?.isEnabled ?: false
    }
}