package archtree.list

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import archtree.list.adapter.bindable.BindableRecyclerViewAdapter
import archtree.list.adapter.pageable.DefaultPageableRecyclerViewLayoutAdapter
import archtree.list.adapter.pageable.PageableRecyclerViewAdapter
import archtree.list.item.BindableListItem

class PageableRecyclerViewLayout : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}

@BindingAdapter("archtree_pagedListAdapter")
fun bindListAdapter(container: RecyclerView, adapter: PageableRecyclerViewAdapter?) {
    if (adapter != null) container.adapter = adapter
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("archtree_pagedListSource", "archtree_pagedListItemTemplate", "archtree_pagedListViewModel",
        "archtree_pagedListDataBindingComponent", "archtree_pagedListLifecycleOwner", requireAll = false)
fun <T : BindableListItem, V : ViewModel, D : Any> bindItemsSource(
        container: RecyclerView,
        newItems: PagedList<T>?,
        @LayoutRes newItemLayout: Int,
        newViewModel: V?,
        newDataBindingComponent: D?,
        newLifecycleOwner: LifecycleOwner?) {

    if (newItems != null) {
        if (container.adapter == null || container.adapter !is BindableRecyclerViewAdapter) {
            container.adapter = DefaultPageableRecyclerViewLayoutAdapter(container.context)
        }

        val adapter = container.adapter
        if (adapter != null && adapter is PageableRecyclerViewAdapter) {
            adapter.bindRecyclerView(container)
            adapter.onUpdate(newItems as PagedList<BindableListItem>, newItemLayout, newViewModel,
                    newDataBindingComponent, newLifecycleOwner)
        }
    }
}
