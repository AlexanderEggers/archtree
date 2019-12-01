package archtree.action

import androidx.databinding.BindingAdapter
import android.view.View

@BindingAdapter("archtree_clickAction", "archtree_clickActionParameter", requireAll = false)
fun <T> View.setClickAction(action: Action<T>?, actionParameter: T?) {
    action?.let {
        val actionListener = ActionListener(this@setClickAction, it, actionParameter)
        actionListener.initialiseClick()
        setOnClickListener(actionListener)
    } ?: run { setOnClickListener(null) }
}

@BindingAdapter("archtree_clickLongAction", "archtree_clickLongActionParameter", requireAll = false)
fun <T> View.setClickLongAction(action: Action<T>?, actionParameter: T?) {
    action?.let {
        val actionListener = ActionListener(this@setClickLongAction, it, actionParameter)
        actionListener.initialiseLongClick()
        setOnLongClickListener(actionListener)
    } ?: run { setOnLongClickListener(null) }
}

private class ActionListener<T>
internal constructor(private val view: View, private val action: Action<T>,
                     private val actionParameter: T?) : View.OnClickListener, OnConditionChangedListener<T>, View.OnLongClickListener {

    init {
        if(!action.listeners.contains(this)) action.listeners.add(this)
        view.isEnabled = action.isEnabled(actionParameter)
    }

    fun initialiseClick() {
        view.isClickable = action.canExecuteClick(actionParameter)
    }

    fun initialiseLongClick() {
        view.isLongClickable = action.canExecuteLongClick(actionParameter)
    }

    override fun onClickConditionChanged(action: Action<T>) {
        view.isClickable = action.canExecuteClick(actionParameter)
    }

    override fun onLongClickConditionChanged(action: Action<T>) {
        view.isLongClickable = action.canExecuteLongClick(actionParameter)
    }

    override fun onEnabledConditionChanged(action: Action<T>) {
        view.isEnabled = action.isEnabled(actionParameter)
    }

    override fun onClick(view: View) {
        if (action.canExecuteClick(actionParameter)) {
            view.postDelayed({ action.click(actionParameter) }, action.provideClickDelayTime())
        }
    }

    override fun onLongClick(v: View?): Boolean {
        return if (action.canExecuteLongClick(actionParameter)) {
            view.postDelayed({ action.longClick(actionParameter) }, action.provideLongClickDelayTime())
            true
        } else false
    }
}
