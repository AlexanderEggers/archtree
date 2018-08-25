package archtree.list

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.databinding.adapters.ListenerUtil
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import archtree.list.util.*

class BindableRecyclerViewLayout : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}

@BindingAdapter("archtree_listSource", "archtree_listItemTemplate")
fun <T : BindableListItem> bindItemsSource(
        container: RecyclerView,
        oldItems: List<T>?,
        @LayoutRes oldItemLayout: Int,
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int) {
    bindItemsSource<T, ViewModel, Any>(container, oldItems, oldItemLayout,
            null, null, newItems, newItemLayout,
            null, null)
}

@BindingAdapter("archtree_listSource", "archtree_listItemTemplate", "archtree_listViewModel")
fun <T : BindableListItem, V : ViewModel> bindItemsSource(
        container: RecyclerView,
        oldItems: List<T>?,
        @LayoutRes oldItemLayout: Int,
        oldViewModel: V?,
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int,
        newViewModel: V?) {
    bindItemsSource<T, ViewModel, Any>(container, oldItems, oldItemLayout,
            oldViewModel, null, newItems, newItemLayout,
            newViewModel, null)
}

@BindingAdapter("archtree_listSource", "archtree_listItemTemplate", "archtree_listDataBindingComponent")
fun <T : BindableListItem, D: Any> bindItemsSource(
        container: RecyclerView,
        oldItems: List<T>?,
        @LayoutRes oldItemLayout: Int,
        oldDatabindingComponent: D?,
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int,
        newDatabindingComponent: D?) {
    bindItemsSource<T, ViewModel, D>(container, oldItems, oldItemLayout,
            null, oldDatabindingComponent, newItems, newItemLayout,
            null, newDatabindingComponent)
}

@BindingAdapter("archtree_listSource", "archtree_listItemTemplate", "archtree_listViewModel", "archtree_listDataBindingComponent")
fun <T : BindableListItem, V : ViewModel, D: Any> bindItemsSource(
        container: RecyclerView,
        oldItems: List<T>?,
        @LayoutRes oldItemLayout: Int,
        oldViewModel: V?,
        oldDataBindingComponent: D?,
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int,
        newViewModel: V?,
        newDataBindingComponent: D?) {

    if (oldItems === newItems
            && oldItemLayout == newItemLayout
            && oldViewModel == newViewModel
            && oldDataBindingComponent == newDataBindingComponent) {
        // Nothing changed
        return
    }

    val adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>(), BindableListAdapter {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val realDataBindingComponent: DataBindingComponent? = if (newDataBindingComponent != null) {
                try {
                    newDataBindingComponent as? DataBindingComponent?
                } catch (e: ClassCastException) {
                    null
                }
            } else null

            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(container.context),
                    newItemLayout,
                    parent,
                    false,
                    realDataBindingComponent
            )

            return DataContextAwareViewHolder<T, V>(binding)
        }

        @Suppress("UNCHECKED_CAST")
        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
            if (viewHolder is DataContextAwareViewHolder<*, *>) {
                (viewHolder as DataContextAwareViewHolder<T, V>).bind(newItems!![position], newViewModel)
            }
        }

        override fun getItemCount(): Int {
            return newItems?.size ?: 0
        }
    }

    val listener: OnListChangedCallbackAdapter<T>? = ListenerUtil.getListener(container, R.id.listChangedListener)
    BindableListUtil.initialiseListBinding(oldItems, newItems, listener, adapter, container)
    container.adapter = adapter
}
