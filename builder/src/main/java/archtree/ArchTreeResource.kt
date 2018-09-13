package archtree

import android.annotation.SuppressLint
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import archtree.viewmodel.BaseViewModel

abstract class ArchTreeResource<ViewModel : BaseViewModel> constructor(builder: ArchTreeBuilder<ViewModel, *>) {

    protected var layer: ArchTreeLayer<ViewModel>? = builder.layer

    var view: View? = null
        private set
    var binding: ViewDataBinding? = null
        private set

    val layoutId: Int = builder.layoutId
    val bindingKey: Int = builder.bindingKey

    val viewModelClass: Class<ViewModel>? = builder.viewModelClass
    val skipViewModelInit: Boolean = builder.skipViewModelInit

    val dataBindingComponent: DataBindingComponent? = builder.dataBindingComponent
    val dataBindingComponentBindingKey: Int = builder.dataBindingComponentBindingKey

    val bundle: Bundle? = builder.bundle

    val toolbarViewId: Int? = builder.toolbarViewId
    val toolbarTitle: String? = builder.toolbarTitle
    val toolbarIcon: Int? = builder.toolbarIcon
    val displayHomeAsUpEnabled: Boolean = builder.displayHomeAsUpEnabled

    @SuppressLint("LogNotTimber")
    open fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false, dataBindingComponent)

        view = if (binding == null) {
            Log.d(ArchTreeResource::class.java.name, "Did you forget to define your layout " +
                    "using <layout>...</layout>? Inflating layout using the default " +
                    "LayoutInflater.inflate(...).")
            inflater.inflate(layoutId, container, false)
        } else {
            binding!!.root
        }

        if (binding != null && dataBindingComponentBindingKey != -1) {
            binding!!.setVariable(dataBindingComponentBindingKey, dataBindingComponent)
        }

        return view
    }
}