package org.archtree.activity

import org.archtree.ArchTreeBuilder
import org.archtree.viewmodel.BaseViewModel

open class ActivityBuilder<ViewModel : BaseViewModel> : ArchTreeBuilder<ViewModel, ActivityBuilder<ViewModel>>() {

    open fun build(layer: ActivityLayer<ViewModel>): ActivityResource<ViewModel> {
        internalBuild(layer)
        return ActivityResource(this)
    }

    open fun build(): ActivityResource<ViewModel> {
        internalBuild(ActivityLayer())
        return ActivityResource(this)
    }
}
