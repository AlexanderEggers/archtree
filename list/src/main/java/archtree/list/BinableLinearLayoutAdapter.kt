package archtree.list

import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

abstract class BinableLinearLayoutAdapter {

    private val mViewHolderList = ArrayList<ViewHolder>()
    private lateinit var viewGroup: ViewGroup

    protected abstract val itemCount: Int

    protected abstract fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): ViewHolder

    protected abstract fun onBindViewHolder(viewHolder: ViewHolder, position: Int)

    fun bindViewGroup(viewGroup: ViewGroup) {
        this.viewGroup = viewGroup
        notifyDataSetChanged()
    }

    fun notifyDataSetChanged() {
        viewGroup.removeAllViews()
        mViewHolderList.clear()

        for (i in 0 until itemCount) {
            mViewHolderList.add(onCreateViewHolder(viewGroup, getType(i)))
        }

        for (i in mViewHolderList.indices) {
            val viewHolder = mViewHolderList[i]
            onBindViewHolder(viewHolder, i)
            viewGroup.addView(viewHolder.itemLayout)
        }
    }

    private fun getType(position: Int): Int {
        return NO_TYPE_SET
    }

    abstract class ViewHolder(internal val itemLayout: View)

    companion object {
        private const val NO_TYPE_SET = 0
    }
}
