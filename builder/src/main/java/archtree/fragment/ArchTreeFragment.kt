package archtree.fragment

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import archknife.annotation.util.Injectable
import archtree.viewmodel.BaseViewModel
import javax.inject.Inject

abstract class ArchTreeFragment<ViewModel : BaseViewModel> : Fragment(), Injectable, HasFragmentBuilder<ViewModel> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var fragmentResource: FragmentResource<ViewModel>? = null

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

            if(getFragmentResource()?.activityToolbar == true) {
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

        val builder = FragmentBuilder<ViewModel>()
        fragmentResource = provideFragmentResource(builder)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return fragmentResource!!.onCreateView(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
            if (getBinding() != null) {
                fragmentResource?.getLayer()?.onResume(getViewModel(), getBinding(), getBundle())
            } else {
                fragmentResource?.getLayer()?.onResume(getViewModel(), view, getBundle())
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        getViewModel()?.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getViewModel()?.onActivityResult(requestCode, resultCode, data)
    }

    open fun getFragmentResource(): FragmentResource<ViewModel>? {
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