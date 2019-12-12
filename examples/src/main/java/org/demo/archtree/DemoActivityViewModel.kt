package org.demo.archtree

import androidx.databinding.ObservableArrayList
import android.os.Bundle
import archknife.annotation.ProvideViewModel
import archtree.action.Action
import archtree.viewmodel.BaseViewModel
import autotarget.generated.FragmentTargets
import autotarget.target.TargetService
import javax.inject.Inject

@ProvideViewModel
class DemoActivityViewModel @Inject constructor(private val targetService: TargetService) : BaseViewModel() {

    val observableArrayList = ObservableArrayList<BindableListItemImpl>()

    val action: Action<Any> = object: Action<Any>() {

        override fun onClick(parameter: Any?) {
            targetService.execute(FragmentTargets.showDemoFragment())

            /*observableArrayList.clear()
            observableArrayList.add(BindableListItemImpl("Test1"))
            observableArrayList.add(BindableListItemImpl("Test2"))
            observableArrayList.add(BindableListItemImpl("Test3"))
            observableArrayList.add(BindableListItemImpl("Test4"))
            observableArrayList.add(BindableListItemImpl("Test5"))
            observableArrayList.add(BindableListItemImpl("Test7"))*/
        }
    }

    override fun onInit(resourceBundle: Bundle?, customBundle: Bundle?, savedInstanceBundle: Bundle?) {
        super.onInit(resourceBundle, customBundle, savedInstanceBundle)

        observableArrayList.add(BindableListItemImpl("Test1"))
        observableArrayList.add(BindableListItemImpl("Test2"))
        observableArrayList.add(BindableListItemImpl("Test3"))
        observableArrayList.add(BindableListItemImpl("Test4"))
        observableArrayList.add(BindableListItemImpl("Test5"))
        observableArrayList.add(BindableListItemImpl("Test6"))
    }
}
