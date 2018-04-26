package org.archtree

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.View
import org.archtree.viewmodel.BaseViewModel

abstract class ArchTreeLayer<in ViewModel : BaseViewModel> {

    @CallSuper
    fun onResume(viewModel: ViewModel?, binding: ViewDataBinding?, bundle: Bundle?) {
    }

    @CallSuper
    fun onResume(viewModel: ViewModel?, view: View?, bundle: Bundle?) {
    }

    @CallSuper
    fun onCreate(viewModel: ViewModel?, binding: ViewDataBinding?, bundle: Bundle?) {
    }

    @CallSuper
    fun onCreate(viewModel: ViewModel?, view: View?, bundle: Bundle?) {
    }

    @CallSuper
    fun onStop(viewModel: ViewModel?, binding: ViewDataBinding?) {
    }

    @CallSuper
    fun onStop(viewModel: ViewModel?, view: View?) {
    }
}