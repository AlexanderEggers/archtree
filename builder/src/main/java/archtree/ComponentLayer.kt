package archtree

import android.os.Bundle
import archtree.viewmodel.BaseViewModel

abstract class ComponentLayer {

    open fun onResume(viewModel: BaseViewModel?) {
        //do nothing by default
    }

    open fun onCreate(viewModel: BaseViewModel?, savedInstanceState: Bundle?) {
        //do nothing by default
    }

    open fun onStart(viewModel: BaseViewModel?) {
        //do nothing by default
    }

    open fun onStop(viewModel: BaseViewModel?) {
        //do nothing by default
    }

    open fun onPause(viewModel: BaseViewModel?) {
        //do nothing by default
    }

    open fun onDestroy(viewModel: BaseViewModel?) {
        //do nothing by default
    }
}