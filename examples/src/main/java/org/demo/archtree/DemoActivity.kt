package org.demo.archtree

import android.databinding.ViewDataBinding
import android.os.Bundle
import org.archknife.annotation.ProvideActivity
import org.archtree.activity.ActivityBuilder
import org.archtree.activity.ActivityLayer
import org.archtree.activity.ActivityResource
import org.archtree.activity.ArchTreeActivity
import org.autotarget.annotation.ActivityTarget
import org.autotarget.generated.FragmentTargets
import org.autotarget.service.TargetService
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
                        targetService.execute(FragmentTargets.showDemoFragment())
                    }
                })
    }
}