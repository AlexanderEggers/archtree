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

    fun longClick(parameter: T? = null): Boolean {
        val currentTime = System.currentTimeMillis()
        return if(currentTime - timeSinceLastClick > provideClickIntervalTime()) {
            timeSinceLastClick = currentTime
            onLongClick(parameter)
        } else false
    }

    @JvmOverloads
    fun forceClick(parameter: T? = null) {
        timeSinceLastClick = System.currentTimeMillis()
        click(parameter)
    }

    @JvmOverloads
    fun forceLongPress(parameter: T? = null) {
        timeSinceLastClick = System.currentTimeMillis()
        longClick(parameter)
    }

    protected fun onClick(parameter: T?) {
        //do nothing
    }

    protected fun onLongClick(parameter: T?): Boolean {
        return false
    }

    @JvmOverloads
    open fun canExecute(parameter: T? = null): Boolean {
        return true
    }

    open fun provideClickIntervalTime(): Long {
        return 500
    }

    fun notifyConditionChanged() {
        listeners.forEach {
            it.onConditionChanged(this)
        }
    }
}
