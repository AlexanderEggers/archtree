package archtree.action

import java.util.*

abstract class Action<T> {
    val listeners = LinkedList<OnConditionChangedListener<T>>()

    abstract fun execute(parameter: T?)

    open fun canExecute(parameter: T?): Boolean {
        return true
    }

    fun notifyConditionChanged() {
        listeners.forEach {
            it.onConditionChanged(this)
        }
    }
}
