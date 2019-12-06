package archtree.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import archtree.ArchTreeResource
import archtree.ViewModelInitMode
import archtree.viewmodel.BaseViewModel

open class ActivityResource<ViewModel : BaseViewModel>
constructor(builder: ActivityBuilder<ViewModel>) : ArchTreeResource<ViewModel>(builder) {

    val layer = super.componentLayer as ActivityComponentLayer<ViewModel>?
    var hasNavHostFragment: Boolean = builder.hasNavHostFragment

    var viewModel: ViewModel? = null
        private set

    val systemUiVisibility: Int = builder.systemUiVisibility
    var themeRes: Int = builder.themeRes

    val hideSupportBar: Boolean = builder.hideSupportBar

    @SuppressLint("LogNotTimber")
    open fun onCreateViewModel(activity: FragmentActivity, factory: ViewModelProvider.Factory,
                               savedInstanceBundle: Bundle?) {

        if (viewModelClass != null) {
            viewModel = ViewModelProvider(activity.viewModelStore, factory).get(viewModelClass)

            if (binding != null && bindingKey != -1) binding?.setVariable(bindingKey, viewModel)
            else Log.d(ActivityResource::class.java.name, "ViewModel is not attached to layout.")

            val resourceBundle = activity.intent.extras
            if (viewModelInitMode == ViewModelInitMode.FORCE_INIT) viewModel?.init(true, resourceBundle, savedInstanceBundle)
            else if (viewModelInitMode == ViewModelInitMode.NON_FORCE_INIT) viewModel?.init(false, resourceBundle, savedInstanceBundle)
        }
    }
}