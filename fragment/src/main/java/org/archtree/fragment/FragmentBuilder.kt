package org.archtree.fragment

import org.archtree.ArchTreeBuilder
import org.archtree.viewmodel.BaseViewModel

open class FragmentBuilder<ViewModel : BaseViewModel> : ArchTreeBuilder<ViewModel, FragmentBuilder<ViewModel>>() {

    open fun build(layer: FragmentLayer<ViewModel>): FragmentResource<ViewModel> {
        internalBuild(layer)
        return FragmentResource(this)
    }

    open fun build(): FragmentResource<ViewModel> {
        internalBuild(object : FragmentLayer<ViewModel>() {

        })
        return FragmentResource(this)
    }
}
