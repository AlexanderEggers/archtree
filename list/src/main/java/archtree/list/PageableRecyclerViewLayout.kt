package archtree.list

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import archtree.list.adapter.pageable.DefaultPageableRecyclerViewLayoutAdapter
import archtree.list.adapter.pageable.PageableRecyclerViewAdapter
import archtree.list.item.BindableListItem

open class PageableRecyclerViewLayout : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}

@BindingAdapter("archtree_pagedListAdapter")
fun PageableRecyclerViewLayout.bindListAdapter(listAdapter: PageableRecyclerViewAdapter?) {
    adapter = listAdapter
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("archtree_pagedListSource", "archtree_pagedListItemTemplate", "archtree_pagedListViewModel",
        "archtree_pagedListDataBindingComponent", "archtree_pagedListDataBindingComponentKey",
        "archtree_pagedListLifecycleOwner", "archtree_pagedListLifecycleOwnerKey", requireAll = false)
fun <T : BindableListItem, V : ViewModel, D : Any> PageableRecyclerViewLayout.bindItemsSource(
        newItems: PagedList<T>?,
        @LayoutRes newItemLayout: Int?,
        newViewModel: V?,
        newDataBindingComponent: D?,
        newDataBindingComponentKey: Int?,
        newLifecycleOwner: LifecycleOwner?,
        newLifecycleOwnerKey: Int?) {

    if (newItems != null) {
        if (adapter == null || adapter !is PageableRecyclerViewAdapter) {
            adapter = DefaultPageableRecyclerViewLayoutAdapter(context)
        }

        val listAdapter = adapter
        if (listAdapter != null && listAdapter is PageableRecyclerViewAdapter) {
            listAdapter.bindRecyclerView(this@bindItemsSource)
            listAdapter.onUpdate(newItems as PagedList<BindableListItem>, newItemLayout, newViewModel,
                    newDataBindingComponent, newDataBindingComponentKey, newLifecycleOwner,
                    newLifecycleOwnerKey)
        }
    }
}
