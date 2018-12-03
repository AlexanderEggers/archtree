package archtree.list

import android.arch.lifecycle.LifecycleOwner
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

@BindingAdapter("archtree_listAdapter")
fun bindListAdapter(container: RecyclerView, adapter: BindableListAdapter?) {
    if (adapter != null && adapter is RecyclerView.Adapter<*>) container.adapter = adapter
}

@BindingAdapter("archtree_listSource", "archtree_listItemTemplate", "archtree_listViewModel",
        "archtree_listDataBindingComponent", "archtree_listLifecycleOwner", requireAll = false)
fun <T : BindableListItem, V : ViewModel, D : Any> bindItemsSource(
        container: RecyclerView,
        oldItems: List<T>?,
        @LayoutRes oldItemLayout: Int,
        oldViewModel: V?,
        oldDataBindingComponent: D?,
        oldLifecycleOwner: LifecycleOwner?,
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int,
        newViewModel: V?,
        newDataBindingComponent: D?,
        newLifecycleOwner: LifecycleOwner?) {

    if (oldItems === newItems
            && oldItemLayout == newItemLayout
            && oldViewModel == newViewModel
            && oldDataBindingComponent == newDataBindingComponent
            && oldLifecycleOwner == newLifecycleOwner) {
        // Nothing changed
        return
    }

    if (container.adapter == null || container.adapter !is BindableListAdapter) {
        val adapter = createPrivateAdapter(container, newItems, newItemLayout, newViewModel,
                newDataBindingComponent, newLifecycleOwner)

        val listener: OnListChangedCallbackAdapter<T>? = ListenerUtil.getListener(container, R.id.listChangedListener)
        BindableListUtil.initialiseListBinding(oldItems, newItems, listener, adapter, container)
    }
}

private fun <T : BindableListItem, V : ViewModel, D : Any> createPrivateAdapter(
        container: RecyclerView,
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int,
        newViewModel: V?,
        newDataBindingComponent: D?,
        newLifecycleOwner: LifecycleOwner?): BindableListAdapter {

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

            if (newLifecycleOwner != null) binding.setLifecycleOwner(newLifecycleOwner)

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

    container.adapter = adapter
    return adapter
}
