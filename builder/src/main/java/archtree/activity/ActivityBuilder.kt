package archtree.activity

import androidx.navigation.fragment.NavHostFragment
import archtree.ArchTreeBuilder
import archtree.viewmodel.BaseViewModel

open class ActivityBuilder : ArchTreeBuilder<ActivityBuilder>() {

    var systemUiVisibility: Int = 0
        private set
    var themeRes: Int = 0
        private set

    var hideSupportBar: Boolean = false
        private set

    var hasNavHostFragment: Boolean = false
        private set

    /**
     * This method hides the support bar for this activity.
     *
     * @param hideSupportBar Hides the support bar if true.
     */
    open fun setHideSupportBar(hideSupportBar: Boolean): ActivityBuilder {
        this.hideSupportBar = hideSupportBar
        return this
    }

    /**
     * This method sets the system ui visibility flags.
     *
     * @param systemUiVisibility Int that defines the ui visibility
     */
    open fun setSystemUiVisibility(systemUiVisibility: Int): ActivityBuilder {
        this.systemUiVisibility = systemUiVisibility
        return this
    }

    /**
     * This method sets the system ui visibility flags.
     *
     * @param systemUiVisibility Int that defines the ui visibility
     */
    open fun setThemeRes(themeRes: Int): ActivityBuilder {
        this.themeRes = themeRes
        return this
    }

    /**
     * This method sets a flag that indicates if the activity is using the
     * "Android Navigation Architecture Component". By assigning it to "true", the underlying
     * implementation will try to retrieve the [NavHostFragment]. By default this flag is "false".
     *
     * @param hasNavHostFragment Uses the [NavHostFragment] implementation if true.
     */
    open fun setHasNavHostFragment(hasNavHostFragment: Boolean): ActivityBuilder {
        this.hasNavHostFragment = hasNavHostFragment
        return this
    }

    /**
     * This method builds a new activity resource. This object will be used for the activity
     * lifecycle.
     *
     * @param layer Optional layer provider to simplify the usage accessing the viewmodel.
     */
    open fun build(layer: ActivityComponentLayer =
                           object : ActivityComponentLayer() {}): ActivityResource {
        internalBuild(layer)
        return ActivityResource(this)
    }
}
