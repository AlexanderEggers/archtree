package archtree.fragment

import archtree.ArchTreeBuilder
import archtree.viewmodel.BaseViewModel

open class FragmentBuilder<ViewModel : BaseViewModel> : ArchTreeBuilder<ViewModel, FragmentBuilder<ViewModel>>() {

    open fun build(layer: FragmentComponentLayer<ViewModel>): FragmentResource<ViewModel> {
        internalBuild(layer)
        return FragmentResource(this)
    }

    open fun build(): FragmentResource<ViewModel> {
        internalBuild(object : FragmentComponentLayer<ViewModel>() {

        })
        return FragmentResource(this)
    }
}
