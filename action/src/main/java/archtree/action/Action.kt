package archtree.action

import java.util.*

abstract class Action<T> {
    private var timeSinceLastClick = System.currentTimeMillis()

    val listeners = LinkedList<OnConditionChangedListener<T>>()

    @JvmOverloads
    fun execute(parameter: T? = null) {
        val currentTime = System.currentTimeMillis()
        if(currentTime - timeSinceLastClick > provideClickIntervalTime()) {
            timeSinceLastClick = currentTime
            onExecute(parameter)
        }
    }

    @JvmOverloads
    fun forceExecute(parameter: T? = null) {
        timeSinceLastClick = System.currentTimeMillis()
        onExecute(parameter)
    }

    protected abstract fun onExecute(parameter: T?)

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
