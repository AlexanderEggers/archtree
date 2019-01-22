package archtree.fragment

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import archtree.ArchTreeResource
import archtree.viewmodel.BaseViewModel

open class FragmentResource<ViewModel : BaseViewModel>
constructor(builder: FragmentBuilder<ViewModel>) : ArchTreeResource<ViewModel>(builder) {

    var viewModel: ViewModel? = null
        private set

    @SuppressLint("LogNotTimber")
    open fun onCreateViewModel(fragment: Fragment, factory: ViewModelProvider.Factory,
                               savedInstanceBundle: Bundle?) {

        viewModel = ViewModelProviders.of(fragment, factory).get(viewModelClass!!)

        if (binding != null && bindingKey != -1) binding?.setVariable(bindingKey, viewModel)
        else Log.w(FragmentResource::class.java.name, "ViewModel is not attached to layout.")

        binding?.setLifecycleOwner(fragment)
        if (lifecycleOwnerBindingKey != -1) binding?.setVariable(lifecycleOwnerBindingKey, fragment as LifecycleOwner)

        if (!skipViewModelInit) viewModel?.init(false, resourceBundle, savedInstanceBundle)
    }

    open fun getLayer(): FragmentLayer<ViewModel> {
        return super.layer as FragmentLayer<ViewModel>
    }
}