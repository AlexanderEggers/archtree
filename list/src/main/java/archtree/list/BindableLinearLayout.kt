package archtree.list

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import archtree.list.adapter.bindable.BindableLinearLayoutAdapter
import archtree.list.adapter.bindable.BindableListAdapter
import archtree.list.adapter.bindable.DefaultBindableLinearLayoutAdapter
import archtree.list.item.BindableListItem

open class BindableLinearLayout : LinearLayout {

    open var adapter: BindableLinearLayoutAdapter? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}

@BindingAdapter("archtree_listAdapter")
fun BindableLinearLayout.bindListAdapter(layoutAdapter: BindableLinearLayoutAdapter?) {
    adapter = layoutAdapter
}

@BindingAdapter("archtree_listSource", "archtree_listItemTemplate", "archtree_listViewModel",
        "archtree_listDataBindingComponent", "archtree_listDataBindingComponentKey",
        "archtree_listLifecycleOwner", "archtree_listLifecycleOwnerKey", requireAll = false)
fun <T : BindableListItem, V : ViewModel, D : Any> BindableLinearLayout.bindItemsSource(
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int?,
        newViewModel: V?,
        newDataBindingComponent: D?,
        newDataBindingComponentKey: Int?,
        newLifecycleOwner: LifecycleOwner?,
        newLifecycleOwnerKey: Int?) {

    if (newItems != null) {
        if (adapter == null) adapter = DefaultBindableLinearLayoutAdapter(context)

        val listAdapter = adapter
        if (listAdapter != null) {
            listAdapter.bindViewGroup(this@bindItemsSource)
            (listAdapter as BindableListAdapter).onUpdate(newItems, newItemLayout, newViewModel,
                    newDataBindingComponent, newDataBindingComponentKey, newLifecycleOwner,
                    newLifecycleOwnerKey)
        }
    }
}
