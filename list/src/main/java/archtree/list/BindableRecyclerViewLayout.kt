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
fun BindableRecyclerViewLayout.bindListAdapter(listAdapter: BindableRecyclerViewAdapter?) {
    adapter = listAdapter
}

@BindingAdapter("archtree_listSource", "archtree_listItemTemplate", "archtree_listViewModel",
        "archtree_listDataBindingComponent", "archtree_listDataBindingComponentKey",
        "archtree_listLifecycleOwner", "archtree_listLifecycleOwnerKey", requireAll = false)
fun <T : BindableListItem, V : ViewModel, D : Any> BindableRecyclerViewLayout.bindItemsSource(
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int?,
        newViewModel: V?,
        newDataBindingComponent: D?,
        newDataBindingComponentKey: Int?,
        newLifecycleOwner: LifecycleOwner?,
        newLifecycleOwnerKey: Int?) {

    if (newItems != null) {
        if (adapter == null || adapter !is BindableRecyclerViewAdapter) {
            adapter = DefaultBindableRecyclerViewLayoutAdapter(context)
        }

        val listAdapter = adapter
        if (listAdapter != null && listAdapter is BindableRecyclerViewAdapter) {
            listAdapter.bindRecyclerView(this@bindItemsSource)
            listAdapter.onUpdate(newItems, newItemLayout, newViewModel,
                    newDataBindingComponent, newDataBindingComponentKey, newLifecycleOwner,
                    newLifecycleOwnerKey)
        }
    }
}

@BindingAdapter("archtree_recyclerViewItemAnimator")
fun RecyclerView.setRecyclerViewItemAnimator(animator: RecyclerView.ItemAnimator?) {
    itemAnimator = animator
}

@BindingAdapter("archtree_recyclerViewScrollToPosition")
fun RecyclerView.setRecyclerViewScrollToPosition(scrollToPosition: Int?) {
    scrollToPosition?.run {
        stopScroll()
        scrollToPosition(scrollToPosition)
    }
}

@BindingAdapter("archtree_recyclerViewSmoothScrollToPosition")
fun RecyclerView.setRecyclerViewSmoothScrollToPosition(scrollToPosition: Int?) {
    scrollToPosition?.run {
        stopScroll()
        smoothScrollToPosition(scrollToPosition)
    }
}
