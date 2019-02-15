package archtree.list.adapter.bindable

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BindableLinearLayoutAdapter : BindableListAdapter {

    private val mViewHolderList = ArrayList<RecyclerView.ViewHolder>()
    private var viewGroup: ViewGroup? = null

    protected abstract val itemCount: Int

    protected abstract fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): RecyclerView.ViewHolder

    protected abstract fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int)

    open fun bindViewGroup(viewGroup: ViewGroup) {
        this.viewGroup = viewGroup
    }

    override fun notifyDataSetChanged() {
        viewGroup?.run {
            removeAllViews()
            mViewHolderList.clear()

            for (i in 0 until itemCount) {
                mViewHolderList.add(onCreateViewHolder(this, getType(i)))
            }

            for (i in mViewHolderList.indices) {
                val viewHolder = mViewHolderList[i]
                onBindViewHolder(viewHolder, i)
                addView(viewHolder.itemView)
            }
        }
    }

    open fun getType(position: Int): Int {
        return NO_TYPE_SET
    }

    companion object {
        private const val NO_TYPE_SET = 0
    }
}
