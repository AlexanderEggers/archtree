package archtree.activity

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import archtree.fragment.ArchTreeFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import archtree.viewmodel.BaseViewModel
import autotarget.util.HasFragmentFlow
import javax.inject.Inject

abstract class ArchTreeActivity<ViewModel : BaseViewModel> : AppCompatActivity(),
        HasSupportFragmentInjector, HasFragmentFlow, HasActivityBuilder<ViewModel> {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var activityResource: ActivityResource<ViewModel>? = null

    override fun onResume() {
        super.onResume()

        if (activityResource?.title != null) {
            title = activityResource?.title
        }

        if (getBinding() != null) {
            activityResource?.getLayer()?.onResume(getViewModel(), getBinding(), getBundle())
        } else {
            activityResource?.getLayer()?.onResume(getViewModel(), getView(), getBundle())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityBuilder = ActivityBuilder<ViewModel>()
        activityResource = provideActivityResource(activityBuilder)

        val view = activityResource?.onCreateView(layoutInflater, null)
        setContentView(view)

        if (activityResource?.viewModelClass != null) {
            activityResource?.onCreateViewModel(this, viewModelFactory)
        }

        if(activityResource?.hideSupportBar == true) {
            supportActionBar?.hide()
        } else {
            val toolbarViewId = getActivityResource()?.toolbarViewId
            if(toolbarViewId != null) {
                setSupportActionBar(findViewById(toolbarViewId))
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }

        val systemUiVisibility = getActivityResource()?.systemUiVisibility
        if(systemUiVisibility != 0) {
            window.decorView.systemUiVisibility = getActivityResource()?.systemUiVisibility ?: 0
        }

        if (getBinding() != null) {
            activityResource?.getLayer()?.onCreate(getViewModel(), getBinding(), getBundle())
        } else {
            activityResource?.getLayer()?.onCreate(getViewModel(), getView(), getBundle())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuId = activityResource?.menuId
        if(menuId != null) {
            val inflater = menuInflater
            inflater.inflate(menuId, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        var shouldRunDefaultBackPressed = getViewModel()?.onBackPressed() ?: true
        supportFragmentManager.fragments.forEach { fragment ->
            if(fragment is ArchTreeFragment<*>?) {
                val fragmentShouldRunDefaultBackPressed = fragment?.onBackPressed() ?: true
                if(shouldRunDefaultBackPressed) shouldRunDefaultBackPressed = fragmentShouldRunDefaultBackPressed
            }
        }

        if(shouldRunDefaultBackPressed) {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getViewModel()?.onActivityResult(requestCode, resultCode, data, getBundle())

        supportFragmentManager.fragments.forEach { fragment ->
            if(fragment is ArchTreeFragment<*>?) {
                fragment?.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if(getActivityResource()?.enableDefaultBackPressed == true) {
                    onBackPressed()
                }
            }
            else -> getViewModel()?.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()

        if (getBinding() != null) {
            activityResource?.getLayer()?.onStop(getViewModel(), getBinding())
        } else {
            activityResource?.getLayer()?.onStop(getViewModel(), getView())
        }
    }

    override fun onShowNextFragment(containerId: Int, state: Int, addToBackStack: Boolean, clearBackStack: Boolean, bundle: Bundle?): Boolean {
        return activityResource?.fragmentFlow?.onShowNextFragment(containerId, state, addToBackStack, clearBackStack, bundle) ?: false
    }

    open fun getActivityResource(): ActivityResource<ViewModel>? {
        return activityResource
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    open fun getView(): View? {
        return window.decorView.findViewById(android.R.id.content)
    }

    open fun getViewModel(): ViewModel? {
        return activityResource?.viewModel
    }

    open fun getBinding(): ViewDataBinding? {
        return activityResource?.binding
    }

    private fun getBundle(): Bundle? {
        return activityResource?.bundle
    }
}