package archtree.list

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.databinding.adapters.ListenerUtil
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout

class BindableLinearLayout : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setAdapter(adapter: BindableLinearLayoutAdapter) {
        adapter.bindViewGroup(this)
    }
}

@BindingAdapter("archtree_itemsSource", "archtree_itemTemplate")
fun <T : BindableListItem> bindItemsSource(
        container: BindableLinearLayout,
        oldItems: List<T>?,
        @LayoutRes oldItemLayout: Int,
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int) {
    bindItemsSource<T, ViewModel>(container, oldItems, oldItemLayout,
            null, newItems, newItemLayout, null)
}

@BindingAdapter("archtree_itemsSource", "archtree_itemTemplate", "archtree_parentDataContext")
fun <T : BindableListItem, V : ViewModel> bindItemsSource(
        container: BindableLinearLayout,
        oldItems: List<T>?,
        @LayoutRes oldItemLayout: Int,
        oldViewModel: V?,
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int,
        newViewModel: V?) {

    if (oldItems === newItems
            && oldItemLayout == newItemLayout
            && oldViewModel == newViewModel) {
        // Nothing changed
        return
    }

    val adapter = object : BindableLinearLayoutAdapter() {

        override val itemCount: Int
            get() = newItems?.size ?: 0

        override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): RecyclerView.ViewHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(container.context),
                    newItemLayout,
                    viewGroup,
                    false
            )

            return DataContextAwareViewHolder<T, V>(binding)
        }

        @Suppress("UNCHECKED_CAST")
        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
            if (viewHolder is DataContextAwareViewHolder<*, *>) {
                (viewHolder as DataContextAwareViewHolder<T, V>).bind(newItems!![position], newViewModel)
            }
        }
    }

    val listener: OnListChangedCallbackAdapter<T>? = ListenerUtil.getListener(container, R.id.listChangedListener)
    BindableListUtil.initialiseListBinding(oldItems, newItems, listener, adapter, container)
    container.setAdapter(adapter)
}
