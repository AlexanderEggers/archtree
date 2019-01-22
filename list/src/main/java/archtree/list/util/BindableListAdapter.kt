package archtree.list.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.annotation.LayoutRes

interface BindableListAdapter {

    fun onUpdate(list: List<BindableListItem>, @LayoutRes itemLayout: Int, viewModel: ViewModel?,
                 dataBindingComponent: Any?, lifecycleOwner: LifecycleOwner?)

    fun notifyDataSetChanged()
}