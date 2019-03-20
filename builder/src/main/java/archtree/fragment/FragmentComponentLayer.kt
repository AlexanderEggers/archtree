package archtree.fragment

import archtree.ComponentLayer
import archtree.viewmodel.BaseViewModel

abstract class FragmentComponentLayer<in ViewModel : BaseViewModel> : ComponentLayer<ViewModel>()