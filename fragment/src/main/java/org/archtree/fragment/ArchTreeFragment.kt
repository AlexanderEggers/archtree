package org.archtree.fragment

import android.arch.lifecycle.ViewModelProvider
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.archknife.extension.Injectable
import org.archtree.viewmodel.BaseViewModel
import javax.inject.Inject

abstract class ArchTreeFragment<ViewModel : BaseViewModel> : Fragment(), Injectable, HasFragmentBuilder<ViewModel> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var fragmentResource: FragmentResource<ViewModel>? = null

    override fun onResume() {
        super.onResume()

        if (fragmentResource?.title != null) {
            activity!!.title = fragmentResource?.title
        }

        if (getBinding() != null) {
            fragmentResource?.getLayer()?.onResume(getViewModel(), getBinding(), getBundle())
        } else {
            fragmentResource?.getLayer()?.onResume(getViewModel(), view, getBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val builder = FragmentBuilder<ViewModel>()
        fragmentResource = provideFragmentResource(builder)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = fragmentResource!!.onCreateView(inflater, container)

        if (fragmentResource?.viewModelClass != null) {
            fragmentResource?.onCreateViewModel(this, viewModelFactory)
        }

        if (getBinding() != null) {
            fragmentResource?.getLayer()?.onCreate(getViewModel(), getBinding(), getBundle())
        } else {
            fragmentResource?.getLayer()?.onCreate(getViewModel(), view, getBundle())
        }

        return view
    }

    override fun onStop() {
        super.onStop()

        if (getBinding() != null) {
            fragmentResource?.getLayer()?.onStop(getViewModel(), getBinding())
        } else {
            fragmentResource?.getLayer()?.onStop(getViewModel(), view)
        }
    }

    fun getFragmentResource(): FragmentResource<ViewModel>? {
        return fragmentResource
    }

    fun getViewModel(): ViewModel? {
        return fragmentResource?.viewModel
    }

    fun getBinding(): ViewDataBinding? {
        return fragmentResource?.binding
    }

    private fun getBundle(): Bundle? {
        return fragmentResource?.bundle
    }
}