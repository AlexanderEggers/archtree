package archtree.activity

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.util.Log
import archtree.ArchTreeResource
import archtree.viewmodel.BaseViewModel
import autotarget.util.HasFragmentFlow

open class ActivityResource<ViewModel : BaseViewModel>
constructor(builder: ActivityBuilder<ViewModel>) : ArchTreeResource<ViewModel>(builder) {

    var viewModel: ViewModel? = null
        private set

    val fragmentFlow: HasFragmentFlow? = builder.fragmentFlow
    val menuId: Int? = builder.menuId
    val systemUiVisibility: Int = builder.systemUiVisibility

    val hideSupportBar: Boolean = builder.hideSupportBar
    val toolbarViewId: Int? = builder.toolbarViewId
    val enableDefaultBackPressed: Boolean = builder.enableDefaultBackPressed

    open fun onCreateViewModel(activity: FragmentActivity, factory: ViewModelProvider.Factory) {
        viewModel = ViewModelProviders.of(activity, factory).get(viewModelClass!!)

        if (binding != null && bindingKey != -1) binding!!.setVariable(bindingKey, viewModel)
        else Log.w(ActivityResource::class.java.name, "ViewModel is not attached to layout.")

        if (!skipViewModelInit) {
            viewModel?.init(false, bundle)
            binding?.setLifecycleOwner(activity)
        }
    }

    open fun getLayer(): ActivityLayer<ViewModel> {
        return super.layer as ActivityLayer<ViewModel>
    }
}