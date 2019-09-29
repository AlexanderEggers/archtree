package archtree.helper

import android.content.Context
import android.os.Build
import android.view.accessibility.AccessibilityManager
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun observeForTouchExplorationChanges(): LiveData<Boolean> {
        val am = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager?
        val source = MutableLiveData<Boolean>()

        am?.addTouchExplorationStateChangeListener { source.value = it }
        source.value = am?.isTouchExplorationEnabled ?: false

        return source
    }

    fun isTouchExplorationEnabled(): Boolean {
        val am = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager?
        return am?.isTouchExplorationEnabled ?: false
    }

    fun getAccessibilityManager(): AccessibilityManager? {
        return context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager?
    }
}