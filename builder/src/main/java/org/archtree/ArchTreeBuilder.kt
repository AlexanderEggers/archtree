package org.archtree

import android.os.Bundle
import org.archtree.viewmodel.BaseViewModel
import org.autotarget.util.HasFragmentFlow

@Suppress("UNCHECKED_CAST")
abstract class ArchTreeBuilder<ViewModel : BaseViewModel, out Builder> {

    var layoutId: Int = 0
        private set

    var viewModelClass: Class<ViewModel>? = null
        private set
    var bindingKey = -1
        private set
    var skipViewModelInit: Boolean = false
        private set

    var fragmentFlow: HasFragmentFlow? = null
        private set
    var title: String? = null
        private set
    var bundle: Bundle? = null
        private set
    var layer: ArchTreeLayer<ViewModel>? = null
        private set

    fun setLayoutId(layoutId: Int): Builder {
        this.layoutId = layoutId
        return this as Builder
    }

    fun setViewModel(viewModelClass: Class<ViewModel>, bindingKey: Int = -1, skipViewModelInit: Boolean = false): Builder {
        this.viewModelClass = viewModelClass
        this.bindingKey = bindingKey
        this.skipViewModelInit = skipViewModelInit
        return this as Builder
    }

    fun setFragmentFlow(fragmentFlow: HasFragmentFlow): Builder {
        this.fragmentFlow = fragmentFlow
        return this as Builder
    }

    fun setTitle(title: String): Builder {
        this.title = title
        return this as Builder
    }

    fun setBundle(bundle: Bundle): Builder {
        this.bundle = bundle
        return this as Builder
    }

    protected fun internalBuild(layer: ArchTreeLayer<ViewModel>) {
        this.layer = layer
    }
}