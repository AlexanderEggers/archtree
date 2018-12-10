package archtree.action

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("archtree_action", "archtree_actionParameter", requireAll = false)
fun <T> setAction(view: View, action: Action<T>?, actionParameter: T?) {
    action?.let {
        val actionListener = ActionListener(view, it, actionParameter)
        view.setOnClickListener(actionListener)
        view.setOnLongClickListener(actionListener)
    } ?: throw IllegalStateException("Action object should not be null.")
}

private class ActionListener<T>
internal constructor(private val view: View, private val action: Action<T>,
                     private val actionParameter: T?) : View.OnClickListener, OnConditionChangedListener<T>, View.OnLongClickListener {

    init {
        action.listeners.add(this)
        view.isEnabled = action.canExecuteClick(actionParameter)
        view.isLongClickable = action.canExecuteLongClick(actionParameter)
    }

    override fun onClickConditionChanged(action: Action<T>) {
        view.isEnabled = action.canExecuteClick(actionParameter)
    }

    override fun onLongClickConditionChanged(action: Action<T>) {
        view.isLongClickable = action.canExecuteLongClick(actionParameter)
    }

    override fun onClick(view: View) {
        if (action.canExecuteClick(actionParameter)) action.click(actionParameter)
    }

    override fun onLongClick(v: View?): Boolean {
        return if (action.canExecuteLongClick(actionParameter)) action.longClick(actionParameter)
        else false
    }
}
