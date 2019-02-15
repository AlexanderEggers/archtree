package archtree.list.adapter.bindable

import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import archtree.list.item.BindableListItem

interface BindableListAdapter {

    fun onUpdate(list: List<BindableListItem>, @LayoutRes itemLayout: Int, viewModel: ViewModel?,
                 dataBindingComponent: Any?, lifecycleOwner: LifecycleOwner?)

    fun notifyDataSetChanged()
}