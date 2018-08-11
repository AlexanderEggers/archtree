package archtree.list

import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding

abstract class BindableListItem {
    abstract fun <V: ViewModel> bind(viewModel: V?, binding: ViewDataBinding)
}