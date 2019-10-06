package org.demo.archtree

import androidx.lifecycle.ViewModel
import androidx.databinding.ViewDataBinding
import archtree.list.item.BindableListItem

class BindableListItemImpl(private val value: String): BindableListItem {

    override fun areItemsTheSame(newItem: BindableListItem): Boolean {
        return if(newItem is BindableListItemImpl) value == newItem.value
        else false
    }

    override fun areContentsTheSame(newItem: BindableListItem): Boolean {
        return if(newItem is BindableListItemImpl) value == newItem.value
        else false
    }

    override fun onBind(viewModel: ViewModel?, binding: ViewDataBinding, viewType: Int) {
        binding.setVariable(BR.value, value)
    }
}