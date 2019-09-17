package archtree.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import archtree.ArchTreeResource
import archtree.FragmentDispatcherLayer
import archtree.ViewModelInitMode
import archtree.viewmodel.BaseViewModel

open class FragmentResource<ViewModel : BaseViewModel>
constructor(builder: FragmentBuilder<ViewModel>) : ArchTreeResource<ViewModel>(builder) {

    val layer = super.componentLayer as FragmentComponentLayer<ViewModel>?
    val fragmentDispatcherLayer: FragmentDispatcherLayer? = builder.fragmentDispatcherLayer

    val hasOptionsMenu = builder.menuId != null

    var viewModel: ViewModel? = null
        private set

    @SuppressLint("LogNotTimber")
    open fun onCreateViewModel(fragment: Fragment, factory: ViewModelProvider.Factory,
                               savedInstanceBundle: Bundle?) {

        if (viewModelClass != null) {
            viewModel = ViewModelProviders.of(fragment, factory).get(viewModelClass)

            if (binding != null && bindingKey != -1) binding?.setVariable(bindingKey, viewModel)
            else Log.w(FragmentResource::class.java.name, "ViewModel is not attached to layout.")

            if (viewModelInitMode == ViewModelInitMode.FORCE_INIT) viewModel?.init(true, resourceBundle, savedInstanceBundle)
            else if (viewModelInitMode == ViewModelInitMode.NON_FORCE_INIT) viewModel?.init(false, resourceBundle, savedInstanceBundle)
        }
    }
}