package archtree.list.item

import androidx.lifecycle.ViewModel
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import archtree.list.item.BindableListItem

open class DataContextAwareViewHolder
constructor(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(item: BindableListItem, viewModel: ViewModel?) {
        item.bind(viewModel, binding)
    }
}