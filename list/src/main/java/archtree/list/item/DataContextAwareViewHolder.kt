package archtree.list.item

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

open class DataContextAwareViewHolder
constructor(private val binding: ViewDataBinding, private val viewType: Int) : RecyclerView.ViewHolder(binding.root) {

    open fun onBind(item: BindableListItem, viewModel: ViewModel?) {
        item.onBind(viewModel, binding, viewType)
    }
}