package archtree.fragment

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.content.res.Configuration
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import archknife.annotation.util.Injectable
import archtree.viewmodel.BaseViewModel
import javax.inject.Inject

abstract class ArchTreeFragment<ViewModel : BaseViewModel> : Fragment(), Injectable,
        HasFragmentBuilder<ViewModel>, ArchTreeFragmentCommunicator {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var fragmentResource: FragmentResource<ViewModel>? = null
        private set

    var menu: Menu? = null
        private set

    override fun onResume() {
        super.onResume()
        refreshFragmentToolbar(activity, view, fragmentResource)
        fragmentResource?.getLayer()?.onResume(getViewModel())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val builder = FragmentBuilder<ViewModel>()
        fragmentResource = provideFragmentResource(builder)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return fragmentResource?.onCreateView(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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