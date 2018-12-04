package archtree.list

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import archtree.list.util.*
import java.util.ArrayList

class BindableLinearLayout : LinearLayout {

    var adapter: BindableLinearLayoutAdapter? = null
    set(value) = value!!.bindViewGroup(this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}

@BindingAdapter("archtree_listAdapter")
fun bindListAdapter(container: BindableLinearLayout, adapter: BindableLinearLayoutAdapter?) {
    if (adapter != null && adapter is RecyclerView.Adapter<*>) container.adapter = adapter
}

@BindingAdapter("archtree_listSource", "archtree_listItemTemplate", "archtree_listViewModel",
        "archtree_listDataBindingComponent", "archtree_listLifecycleOwner", requireAll = false)
fun <T : BindableListItem, V : ViewModel, D: Any> bindItemsSource(
        container: BindableLinearLayout,
        newItems: List<T>?,
        @LayoutRes newItemLayout: Int,
        newViewModel: V?,
        newDataBindingComponent: D?,
        newLifecycleOwner: LifecycleOwner?) {

    if (container.adapter == null || container.adapter !is BindableListAdapter) {
        createDefaultAdapter(container)
    }

    val adapter = container.adapter
    if(newItems != null && adapter != null) {
        (adapter as BindableListAdapter).onUpdate(newItems, newItemLayout, newViewModel,
                newDataBindingComponent, newLifecycleOwner)
    }
}

private fun createDefaultAdapter(container: BindableLinearLayout) {
    val adapter = object : BindableLinearLayoutAdapter() {

        private val itemList = ArrayList<BindableListItem>()
        private var itemLayout: Int = 0
        private var viewModel: ViewModel? = null
        private var dataBindingComponent: Any? = null
        private var lifecycleOwner: LifecycleOwner? = null

        override fun onUpdate(list: List<BindableListItem>, itemLayout: Int, viewModel: ViewModel?,
                              dataBindingComponent: Any?, lifecycleOwner: LifecycleOwner?) {

            this.itemLayout = itemLayout
            this.viewModel = viewModel
            this.dataBindingComponent = dataBindingComponent
            this.lifecycleOwner = lifecycleOwner

            itemList.clear()
            itemList.addAll(list)

            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): RecyclerView.ViewHolder {
            val realDataBindingComponent: DataBindingComponent? = if(dataBindingComponent != null) {
                try {
                    dataBindingComponent as? DataBindingComponent?
                } catch (e: ClassCastException) {
                    null
                }
            } else null

            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(container.context),
                    itemLayout,
                    viewGroup,
                    false,
                    realDataBindingComponent
            )

            if (lifecycleOwner != null) binding.setLifecycleOwner(lifecycleOwner)

            return DataContextAwareViewHolder(binding)
        }

        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
            if (viewHolder is DataContextAwareViewHolder) {
                viewHolder.bind(itemList[position], viewModel)
            }
        }

        override val itemCount: Int
            get() = itemList.size
    }

    container.adapter = adapter
}
