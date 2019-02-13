package archtree.list.item

abstract class BindableListItemAdapter: BindableListItem {

    override fun areItemsTheSame(newItem: BindableListItem): Boolean {
        return false
    }

    override fun areContentsTheSame(newItem: BindableListItem): Boolean {
        return false
    }
}