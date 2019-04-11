package archtree.list.item

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

interface BindableListItem {

    fun areItemsTheSame(newItem: BindableListItem): Boolean

    fun areContentsTheSame(newItem: BindableListItem): Boolean

    fun onBind(viewModel: ViewModel?, binding: ViewDataBinding)
}