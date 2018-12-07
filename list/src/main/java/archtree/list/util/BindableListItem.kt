package archtree.list.util

import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding

abstract class BindableListItem {

    open fun areItemsTheSame(newItem: BindableListItem): Boolean {
        return false
    }

    open fun areContentsTheSame(newItem: BindableListItem): Boolean {
        return false
    }

    open fun bind(viewModel: ViewModel?, binding: ViewDataBinding) {
        //do nothing by default
    }
}