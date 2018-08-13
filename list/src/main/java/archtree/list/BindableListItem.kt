package archtree.list

import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding

interface BindableListItem {
    fun bind(viewModel: ViewModel?, binding: ViewDataBinding)
}