package archtree.list.adapter.pageable

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import archtree.list.item.BindableListItem
import archtree.list.util.PageableRecyclerViewDiffCallback

abstract class PageableRecyclerViewAdapter : PagedListAdapter<BindableListItem, RecyclerView.ViewHolder>(PageableRecyclerViewDiffCallback()), PageableListAdapter {

    abstract fun bindRecyclerView(view: RecyclerView)
}