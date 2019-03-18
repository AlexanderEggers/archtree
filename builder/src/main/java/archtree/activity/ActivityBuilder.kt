package archtree.activity

import archtree.ArchTreeBuilder
import archtree.viewmodel.BaseViewModel
import autotarget.util.HasFragmentFlow

open class ActivityBuilder<ViewModel : BaseViewModel> : ArchTreeBuilder<ViewModel, ActivityBuilder<ViewModel>>() {

    var fragmentFlow: HasFragmentFlow? = null
        private set
    var systemUiVisibility: Int = 0
        private set
    var themeRes: Int = 0
        private set

    var hideSupportBar: Boolean = false
        private set

    open fun setFragmentFlow(fragmentFlow: HasFragmentFlow): ActivityBuilder<ViewModel> {
        this.fragmentFlow = fragmentFlow
        return this
    }

    open fun setHideSupportBar(hideSupportBar: Boolean): ActivityBuilder<ViewModel> {
        this.hideSupportBar = hideSupportBar
        return this
    }

    open fun setSystemUiVisibility(systemUiVisibility: Int): ActivityBuilder<ViewModel> {
        this.systemUiVisibility = systemUiVisibility
        return this
    }

    open fun setThemeRes(themeRes: Int): ActivityBuilder<ViewModel> {
        this.themeRes = themeRes
        return this
    }

    open fun build(layer: ActivityLayer<ViewModel>): ActivityResource<ViewModel> {
        internalBuild(layer)
        return ActivityResource(this)
    }

    open fun build(): ActivityResource<ViewModel> {
        internalBuild(object : ActivityLayer<ViewModel>() {

        })
        return ActivityResource(this)
    }
}
