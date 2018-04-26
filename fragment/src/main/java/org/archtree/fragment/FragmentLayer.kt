package org.archtree.fragment

import org.archtree.ArchTreeLayer
import org.archtree.viewmodel.BaseViewModel

abstract class FragmentLayer<in ViewModel : BaseViewModel> : ArchTreeLayer<ViewModel>()