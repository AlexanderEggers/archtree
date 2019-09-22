package archtree.fragment

import archtree.ArchTreeBuilder
import archtree.viewmodel.BaseViewModel

open class FragmentBuilder<ViewModel : BaseViewModel> : ArchTreeBuilder<ViewModel, FragmentBuilder<ViewModel>>() {

    /**
     * This method builds a new fragment resource. This object will be used for the fragment
     * lifecycle.
     *
     * @param layer Optional layer provider to simplify the usage accessing the viewmodel.
     */
    open fun build(layer: FragmentComponentLayer<ViewModel> =
                           object : FragmentComponentLayer<ViewModel>() {}): FragmentResource<ViewModel> {
        internalBuild(layer)
        return FragmentResource(this)
    }
}
