package archtree.preference

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.util.Log
import archtree.ArchTreeResource
import archtree.fragment.FragmentResource
import archtree.viewmodel.BaseViewModel

open class PreferenceFragmentResource<ViewModel : BaseViewModel>
constructor(builder: PreferenceFragmentBuilder<ViewModel>) : ArchTreeResource<ViewModel>(builder) {

    val preferenceFromResource: Int = builder.preferenceFromResource
    val attachPreferenceLayoutToViewId: Int = builder.attachPreferenceLayoutToViewId

    val dividerDrawableRes: Int = builder.dividerDrawableRes
    val dividerHeight: Int = builder.dividerHeight

    val staticPreferenceValues = builder.staticPreferenceValues
    val staticPreferenceValuesVisibility = builder.staticPreferenceValuesVisibility

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

    open fun getLayer(): PreferenceFragmentLayer<ViewModel> {
        return super.layer as PreferenceFragmentLayer<ViewModel>
    }
}