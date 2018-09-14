package archtree.fragment

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import archtree.ArchTreeLayer
import archtree.viewmodel.BaseViewModel

abstract class FragmentLayer<in ViewModel : BaseViewModel> : ArchTreeLayer<ViewModel>() {

    open fun onShow(viewModel: ViewModel?, binding: ViewDataBinding?, bundle: Bundle?) {
        //do nothing by default
    }

    open fun onShow(viewModel: ViewModel?, view: View?, bundle: Bundle?) {
        //do nothing by default
    }

    open fun onHide(viewModel: ViewModel?, binding: ViewDataBinding?, bundle: Bundle?) {
        //do nothing by default
    }

    open fun onHide(viewModel: ViewModel?, view: View?, bundle: Bundle?) {
        //do nothing by default
    }
}