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
fun bindListAdapter(container: BindableLinearLayout, adapter: BindableLinearLayoutAdapter?) {
    if (adapter != null) container.adapter = adapter
}

@BindingAdapter("archtree_listSource", "archtree_listItemTemplate", "archtree_listViewModel",
        "archtree_listDataBindingComponent", "archtree_listDataBindingComponentKey",
        "archtree_listLifecycleOwner", "archtree_listLifecycleOwnerKey", requireAll = false)
fun <T : BindableListItem, V : ViewModel, D : Any> bindItemsSource(
        container: BindableLinearLayout,
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int?,
        newViewModel: V?,
        newDataBindingComponent: D?,
        newDataBindingComponentKey: Int?,
        newLifecycleOwner: LifecycleOwner?,
        newLifecycleOwnerKey: Int?) {

    if (newItems != null) {
        if (container.adapter == null || container.adapter !is BindableListAdapter) {
            container.adapter = DefaultBindableLinearLayoutAdapter(container.context)
        }

        val adapter = container.adapter
        if (adapter != null) {
            adapter.bindViewGroup(container)
            (adapter as BindableListAdapter).onUpdate(newItems, newItemLayout, newViewModel,
                    newDataBindingComponent, newDataBindingComponentKey, newLifecycleOwner,
                    newLifecycleOwnerKey)
        }
    }
}
