package archtree.list.adapter.pageable

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import archtree.list.item.BindableListItem
import archtree.list.item.DataContextAwareViewHolder
import java.lang.ref.WeakReference

open class DefaultPageableRecyclerViewLayoutAdapter(private val context: Context) : PageableRecyclerViewAdapter() {

    private var itemLayout: Int = 0
    private var viewModel: ViewModel? = null
    private var dataBindingComponent: Any? = null
    private var lifecycleOwner: LifecycleOwner? = null

    private var recyclerViewRef = WeakReference<RecyclerView?>(null)

    override fun bindRecyclerView(view: RecyclerView) {
        recyclerViewRef = WeakReference(view)
    }

    override fun onUpdate(list: PagedList<BindableListItem>, itemLayout: Int, viewModel: ViewModel?,
                          dataBindingComponent: Any?, lifecycleOwner: LifecycleOwner?) {

        this.itemLayout = itemLayout
        this.viewModel = viewModel
        this.dataBindingComponent = dataBindingComponent
        this.lifecycleOwner = lifecycleOwner

        submitList(list)
        recyclerViewRef.get()?.scheduleLayoutAnimation()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val realDataBindingComponent: DataBindingComponent? = if (dataBindingComponent != null) {
            try {
                dataBindingComponent as? DataBindingComponent?
            } catch (e: ClassCastException) {
                null
            }
        } else null

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(context),
                itemLayout,
                parent,
                false,
                realDataBindingComponent
        )

        if (lifecycleOwner != null) binding.lifecycleOwner = lifecycleOwner

        return DataContextAwareViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item!= null && viewHolder is DataContextAwareViewHolder) viewHolder.bind(item, viewModel)
    }
}