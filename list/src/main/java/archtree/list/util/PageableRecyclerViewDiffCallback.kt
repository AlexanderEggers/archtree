package archtree.list.util

import androidx.recyclerview.widget.DiffUtil
import archtree.list.item.BindableListItem

class PageableRecyclerViewDiffCallback : DiffUtil.ItemCallback<BindableListItem>() {

    override fun areItemsTheSame(oldItem: BindableListItem, newItem: BindableListItem): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: BindableListItem, newItem: BindableListItem): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}