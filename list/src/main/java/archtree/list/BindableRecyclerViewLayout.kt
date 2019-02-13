package archtree.list

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import archtree.list.item.BindableListItem
import archtree.list.adapter.BindableRecyclerViewAdapter
import archtree.list.adapter.DefaultRecyclerViewLayoutAdapter

class BindableRecyclerViewLayout : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}

@BindingAdapter("archtree_listAdapter")
fun bindListAdapter(container: RecyclerView, adapter: BindableRecyclerViewAdapter?) {
    if (adapter != null) container.adapter = adapter
}

@BindingAdapter("archtree_listSource", "archtree_listItemTemplate", "archtree_listViewModel",
        "archtree_listDataBindingComponent", "archtree_listLifecycleOwner", requireAll = false)
fun <T : BindableListItem, V : ViewModel, D : Any> bindItemsSource(
        container: RecyclerView,
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int,
        newViewModel: V?,
        newDataBindingComponent: D?,
        newLifecycleOwner: LifecycleOwner?) {

    if (newItems != null) {
        if (container.adapter == null || container.adapter !is BindableRecyclerViewAdapter) {
            container.adapter = DefaultRecyclerViewLayoutAdapter(container.context)
        }

        val adapter = container.adapter
        if (adapter != null && adapter is BindableRecyclerViewAdapter) {
            adapter.bindRecyclerView(container)
            adapter.onUpdate(newItems, newItemLayout, newViewModel,
                    newDataBindingComponent, newLifecycleOwner)
        }
    }
}

@BindingAdapter("archtree_recyclerViewItemAnimator")
fun setRecyclerViewItemAnimator(view: BindableRecyclerViewLayout, animator: RecyclerView.ItemAnimator?) {
    view.itemAnimator = animator
}

@BindingAdapter("archtree_recyclerViewScrollToPosition")
fun setRecyclerViewScrollToPosition(view: BindableRecyclerViewLayout, scrollToPosition: Int?) {
    scrollToPosition?.run { view.scrollToPosition(scrollToPosition) }
}
