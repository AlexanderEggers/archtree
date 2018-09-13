package archtree.action

import java.util.*

abstract class Action<T> {
    private var timeSinceLastClick = System.currentTimeMillis()

    val listeners = LinkedList<OnConditionChangedListener<T>>()

    fun execute(parameter: T?) {
        val currentTime = System.currentTimeMillis()
        if(currentTime - timeSinceLastClick > provideClickIntervalTime()) {
            timeSinceLastClick = currentTime
            onExecute(parameter)
        }
    }

    fun forceExecute(parameter: T?) {
        timeSinceLastClick = System.currentTimeMillis()
        onExecute(parameter)
    }

    protected abstract fun onExecute(parameter: T?)

    open fun canExecute(parameter: T?): Boolean {
        return true
    }

    open fun provideClickIntervalTime(): Long {
        return 1000
    }

    fun notifyConditionChanged() {
        listeners.forEach {
            it.onConditionChanged(this)
        }
    }
}
