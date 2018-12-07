package archtree.action

interface OnConditionChangedListener<T> {
    fun onClickConditionChanged(action: Action<T>)
    fun onLongClickConditionChanged(action: Action<T>)
}
