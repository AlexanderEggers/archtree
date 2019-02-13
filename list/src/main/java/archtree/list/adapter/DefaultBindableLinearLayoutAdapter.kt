package archtree.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import archtree.list.item.BindableListItem
import archtree.list.item.DataContextAwareViewHolder
import java.util.ArrayList

open class DefaultBindableLinearLayoutAdapter(private val context: Context): BindableLinearLayoutAdapter() {

    private val itemList = ArrayList<BindableListItem>()
    private var itemLayout: Int = 0
    private var viewModel: ViewModel? = null
    private var dataBindingComponent: Any? = null
    private var lifecycleOwner: LifecycleOwner? = null

    override fun onUpdate(list: List<BindableListItem>, itemLayout: Int, viewModel: ViewModel?,
                          dataBindingComponent: Any?, lifecycleOwner: LifecycleOwner?) {

        this.itemLayout = itemLayout
        this.viewModel = viewModel
        this.dataBindingComponent = dataBindingComponent
        this.lifecycleOwner = lifecycleOwner

        itemList.clear()
        itemList.addAll(list)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): RecyclerView.ViewHolder {
        val realDataBindingComponent: DataBindingComponent? = if(dataBindingComponent != null) {
            try {
                dataBindingComponent as? DataBindingComponent?
            } catch (e: ClassCastException) {
                null
            }
        } else null

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(context),
                itemLayout,
                viewGroup,
                false,
                realDataBindingComponent
        )

        if (lifecycleOwner != null) binding.lifecycleOwner = lifecycleOwner

        return DataContextAwareViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is DataContextAwareViewHolder) {
            viewHolder.bind(itemList[position], viewModel)
        }
    }

    override val itemCount: Int
        get() = itemList.size
}