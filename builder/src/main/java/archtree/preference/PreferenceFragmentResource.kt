package archtree.preference

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.util.Log
import archtree.ArchTreeResource
import archtree.viewmodel.BaseViewModel

open class PreferenceFragmentResource<ViewModel : BaseViewModel>
constructor(builder: PreferenceFragmentBuilder<ViewModel>) : ArchTreeResource<ViewModel>(builder) {

    val preferenceFromResource: Int = builder.preferenceFromResource
    val attachPreferenceLayoutToViewId: Int = builder.attachPreferenceLayoutToViewId

    val dividerDrawableRes: Int = builder.dividerDrawableRes
    val dividerHeight: Int = builder.dividerHeight

    val staticPreferenceValues = builder.staticPreferenceValues
    val staticPreferenceValuesVisiblity = builder.staticPreferenceValuesVisibility

    var viewModel: ViewModel? = null
        private set

    open fun onCreateViewModel(fragment: Fragment, factory: ViewModelProvider.Factory) {
        viewModel = ViewModelProviders.of(fragment, factory).get(viewModelClass!!)

        if (binding != null && bindingKey != -1) binding!!.setVariable(bindingKey, viewModel)
        else Log.w(PreferenceFragmentResource::class.java.name, "ViewModel is not attached to layout.")

        if (!skipViewModelInit) {
            viewModel?.init(false, bundle)
            binding?.setLifecycleOwner(fragment)
        }
    }

    open fun getLayer(): PreferenceFragmentLayer<ViewModel> {
        return super.layer as PreferenceFragmentLayer<ViewModel>
    }
}