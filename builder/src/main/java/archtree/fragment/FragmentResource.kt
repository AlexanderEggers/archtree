package archtree.fragment

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.util.Log
import archtree.ArchTreeResource
import archtree.viewmodel.BaseViewModel

open class FragmentResource<ViewModel : BaseViewModel>
constructor(builder: FragmentBuilder<ViewModel>) : ArchTreeResource<ViewModel>(builder) {

    var viewModel: ViewModel? = null
        private set

    open fun onCreateViewModel(fragment: Fragment, factory: ViewModelProvider.Factory) {
        viewModel = ViewModelProviders.of(fragment, factory).get(viewModelClass!!)

        if (binding != null && bindingKey != -1) binding?.setVariable(bindingKey, viewModel)
        else Log.w(FragmentResource::class.java.name, "ViewModel is not attached to layout.")

        binding?.setLifecycleOwner(fragment)
        if (lifecycleOwnerBindingKey != -1) binding?.setVariable(lifecycleOwnerBindingKey, fragment as LifecycleOwner)

        if (!skipViewModelInit) {
            viewModel?.init(false, bundle)
        }
    }

    open fun getLayer(): FragmentLayer<ViewModel> {
        return super.layer as FragmentLayer<ViewModel>
    }
}