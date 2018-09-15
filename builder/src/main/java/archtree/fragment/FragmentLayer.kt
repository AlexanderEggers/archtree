package archtree.fragment

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import archtree.ArchTreeLayer
import archtree.viewmodel.BaseViewModel

abstract class FragmentLayer<in ViewModel : BaseViewModel> : ArchTreeLayer<ViewModel>()