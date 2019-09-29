package archtree.list.adapter.pageable

import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import archtree.list.item.BindableListItem

interface PageableListAdapter {

    fun onUpdate(list: PagedList<BindableListItem>, @LayoutRes itemLayout: Int, viewModel: ViewModel?,
                 dataBindingComponent: Any?, dataBindingComponentKey: Int?,
                 lifecycleOwner: LifecycleOwner?, lifecycleOwnerKey: Int?)
}