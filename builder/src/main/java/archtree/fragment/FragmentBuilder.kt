package archtree.fragment

import archtree.ArchTreeBuilder
import archtree.viewmodel.BaseViewModel

open class FragmentBuilder : ArchTreeBuilder<FragmentBuilder>() {

    /**
     * This method builds a new fragment resource. This object will be used for the fragment
     * lifecycle.
     *
     * @param layer Optional layer provider to simplify the usage accessing the viewmodel.
     */
    open fun build(layer: FragmentComponentLayer =
                           object : FragmentComponentLayer() {}): FragmentResource {
        internalBuild(layer)
        return FragmentResource(this)
    }
}
