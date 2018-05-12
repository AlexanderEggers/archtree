package archtree.action

interface OnConditionChangedListener<T> {
    fun onConditionChanged(action: Action<T>)
}
