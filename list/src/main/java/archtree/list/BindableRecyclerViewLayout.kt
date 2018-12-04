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
import archtree.list.util.*
import android.support.v7.util.DiffUtil

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
        createDefaultAdapter(container)
    }

    val adapter = container.adapter
    if(newItems != null && adapter != null && adapter is BindableListAdapter) {
        (adapter as BindableListAdapter).onUpdate(newItems, newItemLayout, newViewModel,
                newDataBindingComponent, newLifecycleOwner)
    }
}

private fun createDefaultAdapter(container: RecyclerView) {

    val adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>(), BindableListAdapter {

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

            if(itemList.isNotEmpty()) {
                val diffResult = DiffUtil.calculateDiff(DefaultDiffCallback(this.itemList, list))
                diffResult.dispatchUpdatesTo(this)
            } else itemList.addAll(list)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val realDataBindingComponent: DataBindingComponent? = if (dataBindingComponent != null) {
                try {
                    dataBindingComponent as? DataBindingComponent?
                } catch (e: ClassCastException) {
                    null
                }
            } else null

            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    LayoutInflater.from(container.context),
                    itemLayout,
                    parent,
                    false,
                    realDataBindingComponent
            )

            if (lifecycleOwner != null) binding.setLifecycleOwner(lifecycleOwner)

            return DataContextAwareViewHolder(binding)
        }

        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
            if (viewHolder is DataContextAwareViewHolder) viewHolder.bind(itemList[position], viewModel)
        }

        override fun getItemCount(): Int {
            return itemList.size
        }
    }

    container.adapter = adapter
}
