package org.archtree.activity

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.util.Log
import org.archtree.ArchTreeResource
import org.archtree.viewmodel.BaseViewModel

open class ActivityResource<ViewModel : BaseViewModel>
constructor(builder: ActivityBuilder<ViewModel>) : ArchTreeResource<ViewModel>(builder) {

    var viewModel: ViewModel? = null
        private set

    open fun onCreateViewModel(activity: FragmentActivity, factory: ViewModelProvider.Factory) {
        viewModel = ViewModelProviders.of(activity, factory).get(viewModelClass!!)

        if (binding != null && bindingKey != -1) binding!!.setVariable(bindingKey, viewModel)
        else Log.w(ActivityResource::class.java.name, "ViewModel is not attached to the " +
                "component layout.")

        if (!skipViewModelInit) {
            viewModel?.init(bundle)
            binding?.setLifecycleOwner(activity)
        }
    }

    open fun getLayer(): ActivityLayer<ViewModel> {
        return super.layer as ActivityLayer<ViewModel>
    }
}