package archtree.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import java.util.ArrayList

abstract class BindableLinearLayoutAdapter: BindableListAdapter {

    private val mViewHolderList = ArrayList<RecyclerView.ViewHolder>()
    private lateinit var viewGroup: ViewGroup

    protected abstract val itemCount: Int

    protected abstract fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): RecyclerView.ViewHolder

    protected abstract fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int)

    fun bindViewGroup(viewGroup: ViewGroup) {
        this.viewGroup = viewGroup
        notifyDataSetChanged()
    }

    override fun notifyDataSetChanged() {
        viewGroup.removeAllViews()
        mViewHolderList.clear()

        for (i in 0 until itemCount) {
            mViewHolderList.add(onCreateViewHolder(viewGroup, getType(i)))
        }

        for (i in mViewHolderList.indices) {
            val viewHolder = mViewHolderList[i]
            onBindViewHolder(viewHolder, i)
            viewGroup.addView(viewHolder.itemView)
        }
    }

    protected fun getType(position: Int): Int {
        return NO_TYPE_SET
    }

    companion object {
        private const val NO_TYPE_SET = 0
    }
}
