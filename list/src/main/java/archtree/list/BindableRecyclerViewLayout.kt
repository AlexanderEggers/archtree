package archtree.list

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.databinding.adapters.ListenerUtil
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import archtree.viewmodel.BaseViewModel

class BindableRecyclerViewLayout : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private class ListChangedListener<T>
    internal constructor(private val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        override fun onChanged(sender: ObservableList<T>) {
            adapter.notifyDataSetChanged()
        }

        override fun onItemRangeChanged(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onItemRangeInserted(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onItemRangeMoved(sender: ObservableList<T>, fromPosition: Int, toPosition: Int, itemCount: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onItemRangeRemoved(sender: ObservableList<T>, positionStart: Int, itemCount: Int) {
            adapter.notifyDataSetChanged()
        }
    }

    companion object {

        @JvmStatic
        @BindingAdapter("archtree_itemsSource", "archtree_itemTemplate")
        fun <T : BindableListItem> bindItemsSource(
                container: BindableRecyclerViewLayout,
                oldItems: List<T>?,
                @LayoutRes oldEntryLayout: Int,
                newItems: List<T>?,
                @LayoutRes newEntryLayout: Int) {
            bindItemsSource<T, ViewModel>(container, oldItems, oldEntryLayout,
                    null, newItems, newEntryLayout, null)
        }

        @JvmStatic
        @BindingAdapter("archtree_itemsSource", "archtree_itemTemplate", "archtree_parentDataContext")
        fun <T : BindableListItem, V : ViewModel> bindItemsSource(
                container: BindableRecyclerViewLayout,
                oldItems: List<T>?,
                @LayoutRes oldEntryLayout: Int,
                oldParentDataContext: V?,
                newItems: List<T>?,
                @LayoutRes newEntryLayout: Int,
                newParentDataContext: V?) {

            if (oldItems === newItems
                    && oldEntryLayout == newEntryLayout
                    && oldParentDataContext == newParentDataContext) {
                // Nothing changed
                return
            }

            val baseListAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                    val binding = DataBindingUtil.inflate<ViewDataBinding>(
                            LayoutInflater.from(container.context),
                            newEntryLayout,
                            parent,
                            false
                    )

                    return DataContextAwareViewHolder<T, V>(binding)
                }

                @Suppress("UNCHECKED_CAST")
                override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
                    if (viewHolder is DataContextAwareViewHolder<*, *>) {
                        (viewHolder as DataContextAwareViewHolder<T, V>).bind(newItems!![position], newParentDataContext)
                    }
                }

                override fun getItemCount(): Int {
                    return newItems?.size ?: 0
                }
            }

            val oldItemsObs = if (oldItems is ObservableList<*>) oldItems as? ObservableList<T> else null
            val newItemsObs = if (newItems is ObservableList<*>) newItems as? ObservableList<T> else null

            var listener: ListChangedListener<T>? = ListenerUtil.getListener(container, R.id.listChangedListener)
            if (oldItems != newItems && oldItemsObs != null && listener != null) {
                oldItemsObs.removeOnListChangedCallback(listener)
            }

            if (listener == null) {
                listener = ListChangedListener(baseListAdapter)
                ListenerUtil.trackListener(container, listener, R.id.listChangedListener)
            }

            if (newItems !== oldItems && newItemsObs != null) {
                newItemsObs.addOnListChangedCallback(listener)
            }

            container.adapter = baseListAdapter
        }

        private class DataContextAwareViewHolder<T : BindableListItem, V : ViewModel>
        internal constructor(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

            fun bind(item: T, viewModel: V?) {
                item.bind(viewModel, binding)
                (item as? BaseViewModel)?.init()
            }
        }
    }
}
