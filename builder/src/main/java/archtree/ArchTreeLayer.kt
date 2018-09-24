package archtree

import android.os.Bundle
import archtree.viewmodel.BaseViewModel

abstract class ArchTreeLayer<in ViewModel : BaseViewModel> {

    open fun onResume(viewModel: ViewModel?, bundle: Bundle?) {
        //do nothing by default
    }


    open fun onCreate(viewModel: ViewModel?, bundle: Bundle?) {
        //do nothing by default
    }


    open fun onStart(viewModel: ViewModel?) {
        //do nothing by default
    }

    open fun onStop(viewModel: ViewModel?) {
        //do nothing by default
    }

    open fun onDestroy(viewModel: ViewModel?) {
        //do nothing by default
    }
}