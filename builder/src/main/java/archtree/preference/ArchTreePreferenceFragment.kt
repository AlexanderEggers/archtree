package archtree.preference

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import archknife.annotation.util.Injectable
import archtree.viewmodel.BaseViewModel
import javax.inject.Inject

abstract class ArchTreePreferenceFragment<ViewModel : BaseViewModel> : PreferenceFragmentCompat(), Injectable, HasPreferenceFragmentBuilder<ViewModel> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var fragmentResource: PreferenceFragmentResource<ViewModel>? = null

    override fun onResume() {
        super.onResume()
        refreshToolbar()

        if (getBinding() != null) {
            fragmentResource?.getLayer()?.onResume(getViewModel(), getBinding(), getBundle())
        } else {
            fragmentResource?.getLayer()?.onResume(getViewModel(), view, getBundle())
        }
    }

    private fun refreshToolbar() {
        val toolbarViewId = getFragmentResource()?.toolbarViewId
        val toolbarTitle = getFragmentResource()?.toolbarTitle

        if (toolbarViewId != null) {
            val supportActivity = activity as? AppCompatActivity

            if (getFragmentResource()?.activityToolbar == true) {
                supportActivity?.setSupportActionBar(supportActivity.findViewById(toolbarViewId))
            } else {
                supportActivity?.setSupportActionBar(view?.findViewById(toolbarViewId))
            }

            val displayHomeAsUpEnabled = getFragmentResource()?.displayHomeAsUpEnabled ?: false
            supportActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)

            if (toolbarTitle != null) {
                supportActivity?.supportActionBar?.title = toolbarTitle
            }

            val toolbarIcon = getFragmentResource()?.toolbarIcon
            if (toolbarIcon != null) {
                supportActivity?.supportActionBar?.setIcon(toolbarIcon)
            }
        } else if (toolbarTitle != null) {
            activity?.title = toolbarTitle
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val builder = PreferenceFragmentBuilder<ViewModel>()
        fragmentResource = provideFragmentResource(builder)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
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
            val mainView = fragmentResource!!.onCreateView(inflater, container)

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

        fragmentResource?.staticPreferenceValues?.forEach { key, value ->
            val preferenceField = findPreference(key)
            if (preferenceField == null) {
                Log.d(ArchTreePreferenceFragment::class.java.name, "Cannot find preference " +
                        "field. Are you sure that you added that field to your preference xml?")
            } else {
                preferenceField.summary = value
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

        if (getBinding() != null) {
            fragmentResource?.getLayer()?.onCreate(getViewModel(), getBinding(), getBundle())
        } else {
            fragmentResource?.getLayer()?.onCreate(getViewModel(), view, getBundle())
        }
    }

    @CallSuper
    open fun onBackPressed(): Boolean {
        return getViewModel()?.onBackPressed() ?: true
    }

    @CallSuper
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            refreshToolbar()

            if (getBinding() != null) {
                fragmentResource?.getLayer()?.onShow(getViewModel(), getBinding(), getBundle())
            } else {
                fragmentResource?.getLayer()?.onShow(getViewModel(), view, getBundle())
            }
        } else {
            if (getBinding() != null) {
                fragmentResource?.getLayer()?.onHide(getViewModel(), getBinding(), getBundle())
            } else {
                fragmentResource?.getLayer()?.onHide(getViewModel(), view, getBundle())
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (getBinding() != null) {
            fragmentResource?.getLayer()?.onStart(getViewModel(), getBinding())
        } else {
            fragmentResource?.getLayer()?.onStart(getViewModel(), view)
        }
    }

    override fun onStop() {
        super.onStop()

        if (getBinding() != null) {
            fragmentResource?.getLayer()?.onStop(getViewModel(), getBinding())
        } else {
            fragmentResource?.getLayer()?.onStop(getViewModel(), view)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        if (getBinding() != null) {
            fragmentResource?.getLayer()?.onDestroy(getViewModel(), getBinding())
        } else {
            fragmentResource?.getLayer()?.onDestroy(getViewModel(), view)
        }
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return getViewModel()?.onPreferenceTreeClick(preference) ?: false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        getViewModel()?.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getViewModel()?.onActivityResult(requestCode, resultCode, data)
    }

    open fun getFragmentResource(): PreferenceFragmentResource<ViewModel>? {
        return fragmentResource
    }

    open fun getViewModel(): ViewModel? {
        return fragmentResource?.viewModel
    }

    open fun getBinding(): ViewDataBinding? {
        return fragmentResource?.binding
    }

    private fun getBundle(): Bundle? {
        return fragmentResource?.bundle
    }
}