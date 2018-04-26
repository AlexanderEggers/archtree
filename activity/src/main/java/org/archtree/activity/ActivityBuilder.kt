package org.archtree.activity

import org.archtree.ArchTreeBuilder
import org.archtree.viewmodel.BaseViewModel

class ActivityBuilder<ViewModel : BaseViewModel> : ArchTreeBuilder<ViewModel, ActivityBuilder<ViewModel>>() {

    fun build(layer: ActivityLayer<ViewModel>): ActivityResource<ViewModel> {
        internalBuild(layer)
        return ActivityResource(this)
    }

    fun build(): ActivityResource<ViewModel> {
        internalBuild(ActivityLayer())
        return ActivityResource(this)
    }
}
