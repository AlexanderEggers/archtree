package org.archtree.fragment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.util.Log
import org.archtree.ArchTreeResource
import org.archtree.viewmodel.BaseViewModel

open class FragmentResource<ViewModel : BaseViewModel>
constructor(builder: FragmentBuilder<ViewModel>) : ArchTreeResource<ViewModel>(builder) {

    var viewModel: ViewModel? = null
        private set

    open fun onCreateViewModel(fragment: Fragment, factory: ViewModelProvider.Factory) {
        viewModel = ViewModelProviders.of(fragment, factory).get(viewModelClass!!)

        if (binding != null && bindingKey != -1) binding!!.setVariable(bindingKey, viewModel)
        else Log.d(FragmentResource::class.java.name, "ViewModel is not attached to layout. Are you sure about that?")

        if (!skipViewModelInit) {
            viewModel?.init(bundle)
            binding?.setLifecycleOwner(fragment)
        }
    }

    open fun getLayer(): FragmentLayer<ViewModel> {
        return super.layer as FragmentLayer<ViewModel>
    }
}