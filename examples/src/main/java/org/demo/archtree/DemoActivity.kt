package org.demo.archtree

import android.databinding.ViewDataBinding
import android.os.Bundle
import archtree.activity.ActivityBuilder
import archtree.activity.ActivityLayer
import archtree.activity.ActivityResource
import archtree.activity.ArchTreeActivity
import org.archknife.annotation.ProvideActivity
import org.autotarget.annotation.ActivityTarget
import org.autotarget.generated.FragmentTargets
import org.autotarget.service.TargetService
import org.autotarget.util.ContextInjector
import javax.inject.Inject

@ActivityTarget
@ProvideActivity
class DemoActivity: ArchTreeActivity<DemoActivityViewModel>() {

    @Inject
    lateinit var targetService: TargetService

    override fun provideActivityResource(builder: ActivityBuilder<DemoActivityViewModel>): ActivityResource<DemoActivityViewModel> {
        return builder.setViewModel(DemoActivityViewModel::class.java, BR.viewModel)
                .setLayoutId(R.layout.demo_activity_layout)
                .setTitle("Demo Activity")
                .build(object: ActivityLayer<DemoActivityViewModel>(){

                    override fun onCreate(viewModel: DemoActivityViewModel?, binding: ViewDataBinding?, bundle: Bundle?) {
                        super.onCreate(viewModel, binding, bundle)

                        ContextInjector.inject(this@DemoActivity)
                        targetService.execute(FragmentTargets.showDemoFragment())
                    }
                })
    }
}