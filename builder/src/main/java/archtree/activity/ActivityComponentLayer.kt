package archtree.activity

import archtree.ComponentLayer
import archtree.viewmodel.BaseViewModel

abstract class ActivityComponentLayer<in ViewModel : BaseViewModel> : ComponentLayer<ViewModel>()