package archtree.list.util

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModel
import android.support.annotation.LayoutRes

interface BindableListAdapter {

    fun onUpdate(list: List<BindableListItem>, @LayoutRes itemLayout: Int, viewModel: ViewModel?,
                 dataBindingComponent: Any?, lifecycleOwner: LifecycleOwner?)

    fun notifyDataSetChanged()
}