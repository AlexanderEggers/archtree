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
    var layoutId: Int = builder.layoutId
        private set
    var bindingKey: Int = builder.bindingKey
        private set

    var viewModelClass: Class<ViewModel>? = builder.viewModelClass
        private set
    var binding: ViewDataBinding? = null
        private set
    var skipViewModelInit: Boolean = builder.skipViewModelInit
        private set

    var dataBindingComponent: DataBindingComponent? = builder.dataBindingComponent
    var dataBindingComponentBindingKey: Int = builder.dataBindingComponentBindingKey

    var bundle: Bundle? = builder.bundle
        private set

    var toolbarViewId: Int? = builder.toolbarViewId
        private set
    var toolbarTitle: String = builder.toolbarTitle
        private set
    var toolbarIcon: Int? = builder.toolbarIcon
        private set

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