package archtree.action

import java.util.*

abstract class Action<T> {
    private var timeSinceLastClick = 0L

    val listeners = LinkedList<OnConditionChangedListener<T>>()

    @JvmOverloads
    fun click(parameter: T? = null) {
        val currentTime = System.currentTimeMillis()
        if(currentTime - timeSinceLastClick > provideClickIntervalTime()) {
            timeSinceLastClick = currentTime
            onClick(parameter)
        }
    }

    @JvmOverloads
    fun longClick(parameter: T? = null): Boolean {
        return onLongClick(parameter)
    }

    @JvmOverloads
    fun forceClick(parameter: T? = null) {
        timeSinceLastClick = System.currentTimeMillis()
        onClick(parameter)
    }

    @JvmOverloads
    fun forceLongPress(parameter: T? = null) {
        longClick(parameter)
    }

    protected open fun onClick(parameter: T?) {
        //do nothing
    }

    protected open fun onLongClick(parameter: T?): Boolean {
        return false
    }

    @JvmOverloads
    open fun isEnabled(parameter: T? = null): Boolean {
        return true
    }

    @JvmOverloads
    open fun canExecuteClick(parameter: T? = null): Boolean {
        return true
    }

    @JvmOverloads
    open fun canExecuteLongClick(parameter: T? = null): Boolean {
        return true
    }

    open fun provideClickIntervalTime(): Long {
        return 500
    }

    open fun provideClickDelayTime(): Long {
        return 0
    }

    open fun provideLongClickDelayTime(): Long {
        return 0
    }

    fun notifyClickConditionChanged() {
        listeners.forEach { it.onClickConditionChanged(this) }
    }

    fun notifyLongClickConditionChanged() {
        listeners.forEach { it.onLongClickConditionChanged(this) }
    }

    fun notifyEnabledConditionChanged() {
        listeners.forEach { it.onEnabledConditionChanged(this) }
    }
}
