package archtree.activity

import archtree.viewmodel.BaseViewModel

interface HasActivityBuilder<ViewModel : BaseViewModel> {

    fun provideActivityResource(builder: ActivityBuilder<ViewModel>): ActivityResource<ViewModel>
}