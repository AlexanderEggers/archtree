package archtree.list.item

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

interface BindableListItem {

    fun onBind(viewModel: ViewModel?, binding: ViewDataBinding, viewType: Int)

    fun areItemsTheSame(newItem: BindableListItem): Boolean = false

    fun areContentsTheSame(newItem: BindableListItem): Boolean = false

    @LayoutRes
    fun onDetermineLayoutRes(viewType: Int): Int? = null

    fun getItemViewType(): Int = 0
}