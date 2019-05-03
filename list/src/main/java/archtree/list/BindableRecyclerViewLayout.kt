package archtree.list

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import archtree.list.adapter.bindable.BindableRecyclerViewAdapter
import archtree.list.adapter.bindable.DefaultBindableRecyclerViewLayoutAdapter
import archtree.list.item.BindableListItem

open class BindableRecyclerViewLayout : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}

@BindingAdapter("archtree_listAdapter")
fun bindListAdapter(container: RecyclerView, adapter: BindableRecyclerViewAdapter?) {
    if (adapter != null) container.adapter = adapter
}

@BindingAdapter("archtree_listSource", "archtree_listItemTemplate", "archtree_listViewModel",
        "archtree_listDataBindingComponent", "archtree_listDataBindingComponentKey",
        "archtree_listLifecycleOwner", "archtree_listLifecycleOwnerKey", requireAll = false)
fun <T : BindableListItem, V : ViewModel, D : Any> bindItemsSource(
        container: BindableRecyclerViewLayout,
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int,
        newViewModel: V?,
        newDataBindingComponent: D?,
        newDataBindingComponentKey: Int?,
        newLifecycleOwner: LifecycleOwner?,
        newLifecycleOwnerKey: Int?) {

    if (newItems != null) {
        if (container.adapter == null || container.adapter !is BindableRecyclerViewAdapter) {
            container.adapter = DefaultBindableRecyclerViewLayoutAdapter(container.context)
        }

        val adapter = container.adapter
        if (adapter != null && adapter is BindableRecyclerViewAdapter) {
            adapter.bindRecyclerView(container)
            adapter.onUpdate(newItems, newItemLayout, newViewModel,
                    newDataBindingComponent, newDataBindingComponentKey, newLifecycleOwner,
                    newLifecycleOwnerKey)
        }
    }
}

@BindingAdapter("archtree_recyclerViewItemAnimator")
fun setRecyclerViewItemAnimator(view: RecyclerView, animator: RecyclerView.ItemAnimator?) {
    view.itemAnimator = animator
}

@BindingAdapter("archtree_recyclerViewScrollToPosition")
fun setRecyclerViewScrollToPosition(view: RecyclerView, scrollToPosition: Int?) {
    scrollToPosition?.run {
        view.stopScroll()
        view.scrollToPosition(scrollToPosition)
    }
}

@BindingAdapter("archtree_recyclerViewSmoothScrollToPosition")
fun setRecyclerViewSmoothScrollToPosition(view: RecyclerView, scrollToPosition: Int?) {
    scrollToPosition?.run {
        view.stopScroll()
        view.smoothScrollToPosition(scrollToPosition)
    }
}
