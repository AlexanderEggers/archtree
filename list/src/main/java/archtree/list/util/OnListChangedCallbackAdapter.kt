package archtree.list.util

import android.databinding.ObservableList

open class OnListChangedCallbackAdapter<T>
constructor(private val adapter: BindableListAdapter): ObservableList.OnListChangedCallback<ObservableList<T>>() {

    override fun onChanged(sender: ObservableList<T>) {
        updateAdapter()
    }

    override fun onItemRangeChanged(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
        updateAdapter()
    }

    override fun onItemRangeInserted(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
        updateAdapter()
    }

    override fun onItemRangeMoved(sender: ObservableList<T>, fromPosition: Int, toPosition: Int, itemCount: Int) {
        updateAdapter()
    }

    override fun onItemRangeRemoved(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
        updateAdapter()
    }

    private fun updateAdapter() {
        adapter.notifyDataSetChanged()
    }
}