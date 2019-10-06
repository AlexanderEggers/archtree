package archtree.helper

import android.content.Context
import android.os.Build
import android.view.accessibility.AccessibilityManager
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AccessibilityHelper(private val context: Context) {

    /**
     * Observe [AccessibilityManager.isEnabled] for any state changes.
     *
     * @return LiveData object that will indicate if [AccessibilityManager.isEnabled] is enabled or
     * not. True means that the AccessibilityManager is enabled, false otherwise.
     * @see AccessibilityManager.isEnabled
     */
    fun observeForAccessibilityChanges(): LiveData<Boolean> {
        val am = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager?
        val source = MutableLiveData<Boolean>()

        am?.addAccessibilityStateChangeListener { source.value = it }
        source.value = am?.isEnabled ?: false

        return source
    }

    /**
     * Get the current AccessibilityManager state.
     *
     * @return True if the AccessibilityManager is enabled, false otherwise.
     * @see AccessibilityManager.isEnabled
     */
    fun isAccessiblityEnabled(): Boolean {
        val am = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager?
        return am?.isEnabled ?: false
    }

    /**
     * Observe the AccessibilityManager for any [AccessibilityManager.isTouchExplorationEnabled]
     * state changes.
     *
     * @return LiveData object that will indicate if the
     * [AccessibilityManager.isTouchExplorationEnabled] is enabled or not. True means it is enabled,
     * false otherwise.
     * @see AccessibilityManager.isTouchExplorationEnabled
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun observeForTouchExplorationChanges(): LiveData<Boolean> {
        val am = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager?
        val source = MutableLiveData<Boolean>()

        am?.addTouchExplorationStateChangeListener { source.value = it }
        source.value = am?.isTouchExplorationEnabled ?: false

        return source
    }

    /**
     * Get the current [AccessibilityManager.isTouchExplorationEnabled] state.
     *
     * @return True if AccessibilityManager.isTouchExplorationEnabled is enabled, false otherwise.
     * @see AccessibilityManager.isTouchExplorationEnabled
     */
    fun isTouchExplorationEnabled(): Boolean {
        val am = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager?
        return am?.isTouchExplorationEnabled ?: false
    }

    /**
     * Get the [AccessibilityManager] instance.
     *
     * @return [AccessibilityManager]
     */
    fun getAccessibilityManager(): AccessibilityManager? {
        return context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager?
    }
}