package archtree.preference

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.content.res.Configuration
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.util.Log
import android.view.*
import archknife.annotation.util.Injectable
import archtree.fragment.ArchTreeFragmentCommunicator
import archtree.fragment.refreshFragmentToolbar
import archtree.viewmodel.BaseViewModel
import javax.inject.Inject

abstract class ArchTreePreferenceFragment<ViewModel : BaseViewModel> : PreferenceFragmentCompat(),
        Injectable, HasPreferenceFragmentBuilder<ViewModel>, ArchTreeFragmentCommunicator {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var fragmentResource: PreferenceFragmentResource<ViewModel>? = null
        private set

    var menu: Menu? = null
        private set

    override fun onResume() {
        super.onResume()
        refreshFragmentToolbar(activity, view, fragmentResource)
        fragmentResource?.getLayer()?.onResume(getViewModel())
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val builder = PreferenceFragmentBuilder<ViewModel>()
        fragmentResource = provideFragmentResource(builder)

        val preferenceFromResource = fragmentResource?.preferenceFromResource ?: -1
        if (preferenceFromResource != -1) {
            addPreferencesFromResource(preferenceFromResource)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val preferenceView = super.onCreateView(inflater, container, savedInstanceState)

        val layoutId = fragmentResource?.layoutId ?: -1
        val preferenceAttachViewId = fragmentResource?.attachPreferenceLayoutToViewId ?: -1

        return if (layoutId != -1 && preferenceAttachViewId != -1) {
            val mainView = fragmentResource?.onCreateView(inflater, container)

            val attachView = mainView?.findViewById<View>(preferenceAttachViewId)
            if (attachView != null && attachView is ViewGroup) {
                attachView.addView(preferenceView)
            } else {
                Log.e(ArchTreePreferenceFragment::class.java.name, "Cannot attach preference " +
                        "view to your custom layout. Make sure that your provided view id is valid " +
                        "and given inside your layout.")
            }

            mainView
        } else {
            Log.d(ArchTreePreferenceFragment::class.java.name, "Using preference layout " +
                    "instead of custom layout. If you want to use your custom layout, you need to " +
                    "provide a valid layout id and a ViewGroup that the preference view can be " +
                    "attached to.")
            preferenceView
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val visibilityMap = fragmentResource?.staticPreferenceValuesVisibility
        fragmentResource?.staticPreferenceValues?.forEach {
            val preferenceField = findPreference(it.key)

            if (preferenceField == null) {
                Log.d(ArchTreePreferenceFragment::class.java.name, "Cannot find preference " +
                        "field. Are you sure that you added that field to your preference xml?")
            } else {
                val visibility = visibilityMap?.get(it.key) ?: true
                preferenceField.isVisible = visibility

                if (it.value != null) {
                    preferenceField.summary = it.value
                }
            }
        }

        val dividerHeight = fragmentResource?.dividerHeight ?: -1
        if (dividerHeight != -1) {
            setDividerHeight(dividerHeight)
        }

        val dividerDrawableRes = fragmentResource?.dividerDrawableRes ?: -1
        val contextRef = context
        if (contextRef != null && dividerDrawableRes != -1) {
            val drawable = ContextCompat.getDrawable(contextRef, dividerDrawableRes)
            setDivider(drawable)
        }

        if (fragmentResource?.viewModelClass != null) {
            fragmentResource?.onCreateViewModel(this, viewModelFactory)
        }

        fragmentResource?.getLayer()?.onCreate(getViewModel(), savedInstanceState)
    }

    override fun onBackPressed(): Boolean {
        return getViewModel()?.onBackPressed() ?: false
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) onResume()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        getViewModel()?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onStart() {
        super.onStart()
        fragmentResource?.getLayer()?.onStart(getViewModel())
    }

    override fun onStop() {
        super.onStop()
        fragmentResource?.getLayer()?.onStop(getViewModel())
    }

    override fun onPause() {
        super.onPause()
        fragmentResource?.getLayer()?.onPause(getViewModel())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentResource?.getLayer()?.onDestroy(getViewModel())
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return getViewModel()?.onPreferenceTreeClick(preference) ?: false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return getViewModel()?.onOptionsItemSelected(item) ?: false
    }

    override fun onFragmentActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return getViewModel()?.onActivityResult(requestCode, resultCode, data) ?: false
    }

    override fun onFragmentRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray): Boolean {
        return getViewModel()?.onRequestPermissionsResult(requestCode, permissions, grantResults)
                ?: false
    }

    override fun onFragmentConfigurationChanged(newConfig: Configuration?): Boolean {
        return getViewModel()?.onConfigurationChanged(newConfig) ?: false
    }

    override fun onFragmentNewIntent(intent: Intent?): Boolean {
        return getViewModel()?.onNewIntent(intent) ?: false
    }

    override fun onFragmentCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu

        val menuId = fragmentResource?.menuId
        if (menuId != null) {
            activity?.menuInflater?.inflate(menuId, menu)
        }

        return getViewModel()?.onCreateOptionsMenu(menu) ?: false
    }

    open fun getViewModel(): ViewModel? {
        return fragmentResource?.viewModel
    }

    open fun getBinding(): ViewDataBinding? {
        return fragmentResource?.binding
    }

    open fun getResourceBundle(): Bundle? {
        return fragmentResource?.bundle
    }
}