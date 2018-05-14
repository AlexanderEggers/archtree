package org.demo.archtree

import archknife.annotation.ProvideViewModel
import archtree.action.Action
import archtree.viewmodel.BaseViewModel
import autotarget.generated.FragmentTargets
import autotarget.service.TargetService
import javax.inject.Inject

@ProvideViewModel
class DemoActivityViewModel @Inject constructor(private val targetService: TargetService) : BaseViewModel() {

    val action: Action<Any> = object: Action<Any>() {

        override fun execute(parameter: Any?) {
            targetService.execute(FragmentTargets.showDemoFragment())
        }
    }
}
