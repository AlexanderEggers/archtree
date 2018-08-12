package archtree.list

import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import archtree.viewmodel.BaseViewModel

open class DataContextAwareViewHolder<T : BindableListItem, V : ViewModel>
constructor(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(item: T, viewModel: V?) {
        item.bind(viewModel, binding)
        (item as? BaseViewModel)?.init()
    }
}