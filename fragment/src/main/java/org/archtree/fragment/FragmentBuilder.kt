package org.archtree.fragment

import org.archtree.ArchTreeBuilder
import org.archtree.viewmodel.BaseViewModel

class FragmentBuilder<ViewModel : BaseViewModel> : ArchTreeBuilder<ViewModel, FragmentBuilder<ViewModel>>() {

    fun build(layer: FragmentLayer<ViewModel>): FragmentResource<ViewModel> {
        internalBuild(layer)
        return FragmentResource(this)
    }

    fun build(): FragmentResource<ViewModel> {
        internalBuild(object : FragmentLayer<ViewModel>() {

        })
        return FragmentResource(this)
    }
}
