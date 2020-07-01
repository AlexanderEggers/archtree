package archtree.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import archtree.ArchTreeResource
import archtree.ViewModelInitMode
import archtree.viewmodel.BaseViewModel

open class FragmentResource
constructor(builder: FragmentBuilder) : ArchTreeResource(builder) {

    val layer = super.componentLayer as FragmentComponentLayer?

    val hasOptionsMenu = builder.menuId != null

    var viewModel: BaseViewModel? = null
        private set

    @SuppressLint("LogNotTimber")
    open fun onCreateViewModel(fragment: Fragment, factory: ViewModelProvider.Factory,
                               savedInstanceBundle: Bundle?) {

        viewModelNodes.forEach {
            viewModel = ViewModelProvider(fragment.viewModelStore, factory).get(it.viewModelClass)

            if (binding != null && it.bindingKey != -1) binding?.setVariable(it.bindingKey, viewModel)
            else Log.d(FragmentResource::class.java.name, "ViewModel is not attached to layout.")
        }
    }

    open fun onInitViewModel(fragment: Fragment, savedInstanceBundle: Bundle?) {
        val resourceBundle = fragment.arguments
        if (viewModelInitMode == ViewModelInitMode.FORCE_INIT) viewModel?.init(true, resourceBundle, customBundle, savedInstanceBundle)
        else if (viewModelInitMode == ViewModelInitMode.NON_FORCE_INIT) viewModel?.init(false, resourceBundle, customBundle, savedInstanceBundle)
    }
}