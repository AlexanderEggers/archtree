package org.demo.archtree

import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding
import archtree.list.BindableListItem

class BindableListItemImpl(private val value: String): BindableListItem() {

    override fun <V : ViewModel> bind(viewModel: V?, binding: ViewDataBinding) {
        binding.setVariable(BR.value, value)
    }
}