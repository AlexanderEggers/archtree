package archtree

import archtree.viewmodel.BaseViewModel

data class ViewModelNode(val viewModelClass: Class<BaseViewModel>,
                         val bindingKey: Int = -1,
                         val viewModelInitMode: ViewModelInitMode = ViewModelInitMode.NON_FORCE_INIT)