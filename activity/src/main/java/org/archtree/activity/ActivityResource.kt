package org.archtree.activity

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.util.Log
import org.archtree.ArchTreeResource
import org.archtree.viewmodel.BaseViewModel

class ActivityResource<ViewModel : BaseViewModel>
constructor(builder: ActivityBuilder<ViewModel>) : ArchTreeResource<ViewModel>(builder) {

    var viewModel: ViewModel? = null
        private set

    fun onCreateViewModel(activity: FragmentActivity, factory: ViewModelProvider.Factory) {
        viewModel = ViewModelProviders.of(activity, factory).get(viewModelClass!!)

        if (binding != null && bindingKey != -1) binding!!.setVariable(bindingKey, viewModel)
        else Log.d(ActivityResource::class.java.name, "ViewModel is not attached to layout. Are you sure about that?")

        if (!skipViewModelInit) {
            viewModel?.init(bundle)
            binding?.setLifecycleOwner(activity)
        }
    }

    fun getLayer(): ActivityLayer<ViewModel> {
        return super.layer as ActivityLayer<ViewModel>
    }
}