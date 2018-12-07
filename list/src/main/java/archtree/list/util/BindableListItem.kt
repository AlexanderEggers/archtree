package archtree.list.util

import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding

interface BindableListItem {

    fun areItemsTheSame(newItem: BindableListItem): Boolean

    fun areContentsTheSame(newItem: BindableListItem): Boolean

    fun bind(viewModel: ViewModel?, binding: ViewDataBinding)
}