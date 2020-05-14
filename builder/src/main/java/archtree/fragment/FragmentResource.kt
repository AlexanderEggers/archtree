package archtree.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import archtree.ArchTreeResource
import archtree.ViewModelInitMode
import archtree.viewmodel.BaseViewModel

open class FragmentResource<ViewModel : BaseViewModel>
constructor(builder: FragmentBuilder<ViewModel>) : ArchTreeResource<ViewModel>(builder) {

    val layer = super.componentLayer as FragmentComponentLayer<ViewModel>?

    val hasOptionsMenu = builder.menuId != null

    var viewModel: ViewModel? = null
        private set

    @SuppressLint("LogNotTimber")
    open fun onCreateViewModel(fragment: Fragment, factory: ViewModelProvider.Factory) {
        if (viewModelClass != null) {
            viewModel = ViewModelProvider(fragment.viewModelStore, factory).get(viewModelClass)

            if (binding != null && bindingKey != -1) binding?.setVariable(bindingKey, viewModel)
            else Log.d(FragmentResource::class.java.name, "ViewModel is not attached to layout.")
        }
    }

    open fun onInitViewModel(fragment: Fragment, savedInstanceBundle: Bundle?) {
        val resourceBundle = fragment.arguments
        if (viewModelInitMode == ViewModelInitMode.FORCE_INIT) viewModel?.init(true, resourceBundle, customBundle, savedInstanceBundle)
        else if (viewModelInitMode == ViewModelInitMode.NON_FORCE_INIT) viewModel?.init(false, resourceBundle, customBundle, savedInstanceBundle)
    }
}