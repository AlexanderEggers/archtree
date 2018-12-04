package archtree.list.util

import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import archtree.viewmodel.BaseViewModel

open class DataContextAwareViewHolder
constructor(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(item: BindableListItem, viewModel: ViewModel?) {
        item.bind(viewModel, binding)
        if(item is BaseViewModel) item.init()
    }
}