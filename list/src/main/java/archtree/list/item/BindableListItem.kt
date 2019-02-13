package archtree.list.item

import androidx.lifecycle.ViewModel
import androidx.databinding.ViewDataBinding

interface BindableListItem {

    fun areItemsTheSame(newItem: BindableListItem): Boolean

    fun areContentsTheSame(newItem: BindableListItem): Boolean

    fun bind(viewModel: ViewModel?, binding: ViewDataBinding)
}