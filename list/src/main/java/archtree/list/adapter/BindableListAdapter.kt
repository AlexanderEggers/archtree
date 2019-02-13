package archtree.list.adapter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.annotation.LayoutRes
import archtree.list.item.BindableListItem

interface BindableListAdapter {

    fun onUpdate(list: List<BindableListItem>, @LayoutRes itemLayout: Int, viewModel: ViewModel?,
                 dataBindingComponent: Any?, lifecycleOwner: LifecycleOwner?)

    fun notifyDataSetChanged()
}