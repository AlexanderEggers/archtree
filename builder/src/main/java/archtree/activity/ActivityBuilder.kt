package archtree.activity

import archtree.ArchTreeBuilder
import archtree.viewmodel.BaseViewModel

open class ActivityBuilder<ViewModel : BaseViewModel> : ArchTreeBuilder<ViewModel, ActivityBuilder<ViewModel>>() {

    var systemUiVisibility: Int = 0
        private set
    var themeRes: Int = 0
        private set

    var hideSupportBar: Boolean = false
        private set

    /**
     * This method hides the support bar for this activity.
     *
     * @param hideSupportBar Hides the support bar if true.
     */
    open fun setHideSupportBar(hideSupportBar: Boolean): ActivityBuilder<ViewModel> {
        this.hideSupportBar = hideSupportBar
        return this
    }

    /**
     * This method sets the system ui visibility flags.
     *
     * @param systemUiVisibility Int that defines the ui visibility
     */
    open fun setSystemUiVisibility(systemUiVisibility: Int): ActivityBuilder<ViewModel> {
        this.systemUiVisibility = systemUiVisibility
        return this
    }

    /**
     * This method sets the system ui visibility flags.
     *
     * @param systemUiVisibility Int that defines the ui visibility
     */
    open fun setThemeRes(themeRes: Int): ActivityBuilder<ViewModel> {
        this.themeRes = themeRes
        return this
    }

    /**
     * This method builds a new activity resource. This object will be used for the activity
     * lifecycle.
     *
     * @param layer Optional layer provider to simplify the usage accessing the viewmodel.
     */
    open fun build(layer: ActivityComponentLayer<ViewModel> =
                           object : ActivityComponentLayer<ViewModel>() {}): ActivityResource<ViewModel> {
        internalBuild(layer)
        return ActivityResource(this)
    }
}
