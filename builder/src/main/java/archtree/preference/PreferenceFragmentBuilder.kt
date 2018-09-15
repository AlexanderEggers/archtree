package archtree.preference

import android.support.annotation.IdRes
import android.support.annotation.XmlRes
import archtree.ArchTreeBuilder
import archtree.viewmodel.BaseViewModel

open class PreferenceFragmentBuilder<ViewModel : BaseViewModel> : ArchTreeBuilder<ViewModel, PreferenceFragmentBuilder<ViewModel>>() {

    var preferenceFromResource: Int = -1
        private set
    var attachPreferenceLayoutToViewId: Int = -1
        private set

    var dividerDrawableRes: Int = -1
        private set
    var dividerHeight: Int = -1
        private set

    val staticPreferenceValues = HashMap<String, String?>()
    val staticPreferenceValuesVisibility = HashMap<String, Boolean>()

    @JvmOverloads
    open fun addStaticPreferenceValue(key: String, value: String? = null, visible: Boolean = true): PreferenceFragmentBuilder<ViewModel> {
        staticPreferenceValues[key] = value
        staticPreferenceValuesVisibility[key] = visible
        return this
    }

    @JvmOverloads
    open fun setPreferenceFromResource(@XmlRes resId: Int, @IdRes attachToViewId: Int = -1): PreferenceFragmentBuilder<ViewModel> {
        preferenceFromResource = resId
        attachPreferenceLayoutToViewId = attachToViewId
        return this
    }

    @JvmOverloads
    open fun setDivider(dividerDrawableRes: Int = -1, dividerHeight: Int = -1): PreferenceFragmentBuilder<ViewModel> {
        this.dividerDrawableRes = dividerDrawableRes
        this.dividerHeight = dividerHeight
        return this
    }

    open fun build(layer: PreferenceFragmentLayer<ViewModel>): PreferenceFragmentResource<ViewModel> {
        internalBuild(layer)
        return PreferenceFragmentResource(this)
    }

    open fun build(): PreferenceFragmentResource<ViewModel> {
        internalBuild(object : PreferenceFragmentLayer<ViewModel>() {

        })
        return PreferenceFragmentResource(this)
    }
}
