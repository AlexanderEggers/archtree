package archtree.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import archtree.ArchTreeResource
import archtree.ViewModelInitMode
import archtree.viewmodel.BaseViewModel
import autotarget.util.HasFragmentFlow

open class ActivityResource<ViewModel : BaseViewModel>
constructor(builder: ActivityBuilder<ViewModel>) : ArchTreeResource<ViewModel>(builder) {

    var viewModel: ViewModel? = null
        private set

    val fragmentFlow: HasFragmentFlow? = builder.fragmentFlow
    val systemUiVisibility: Int = builder.systemUiVisibility
    var themeRes: Int = builder.themeRes

    val hideSupportBar: Boolean = builder.hideSupportBar

    @SuppressLint("LogNotTimber")
    open fun onCreateViewModel(activity: FragmentActivity, factory: ViewModelProvider.Factory,
                               savedInstanceBundle: Bundle?) {

        if (viewModelClass != null) {
            viewModel = ViewModelProviders.of(activity, factory).get(viewModelClass)

            if (binding != null && bindingKey != -1) binding?.setVariable(bindingKey, viewModel)
            else Log.w(ActivityResource::class.java.name, "ViewModel is not attached to layout.")

            if (viewModelInitMode == ViewModelInitMode.FORCE_INIT) viewModel?.init(true, resourceBundle, savedInstanceBundle)
            else if (viewModelInitMode == ViewModelInitMode.NON_FORCE_INIT) viewModel?.init(false, resourceBundle, savedInstanceBundle)
        }
    }

    open fun getLayer(): ActivityLayer<ViewModel> {
        return super.layer as ActivityLayer<ViewModel>
    }
}