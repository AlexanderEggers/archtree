package archtree.list.util

import androidx.recyclerview.widget.DiffUtil
import archtree.list.item.BindableListItem

class DefaultDiffCallback(private val oldList: List<BindableListItem>,
                          private val newList: List<BindableListItem>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].areItemsTheSame(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].areContentsTheSame(newList[newItemPosition])
    }
}