package org.demo.archtree

import androidx.lifecycle.ViewModel
import androidx.databinding.ViewDataBinding
import archtree.list.item.BindableListItem
import archtree.list.item.BindableListItemAdapter

class BindableListItemImpl(private val value: String): BindableListItemAdapter() {

    override fun areItemsTheSame(newItem: BindableListItem): Boolean {
        return if(newItem is BindableListItemImpl) value == newItem.value
        else false
    }

    override fun areContentsTheSame(newItem: BindableListItem): Boolean {
        return if(newItem is BindableListItemImpl) value == newItem.value
        else false
    }

    override fun bind(viewModel: ViewModel?, binding: ViewDataBinding) {
        binding.setVariable(BR.value, value)
    }
}