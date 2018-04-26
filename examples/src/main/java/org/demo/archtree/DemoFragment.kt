package org.demo.archtree

import org.archknife.annotation.ProvideFragment
import org.archtree.fragment.ArchTreeFragment
import org.archtree.fragment.FragmentBuilder
import org.archtree.fragment.FragmentResource
import org.archtree.viewmodel.HasNoViewModel
import org.autotarget.annotation.FragmentTarget

@FragmentTarget(R.id.fragment_container)
@ProvideFragment(DemoActivity::class)
class DemoFragment: ArchTreeFragment<HasNoViewModel>() {

    override fun provideFragmentResource(builder: FragmentBuilder<HasNoViewModel>): FragmentResource<HasNoViewModel> {
        return builder.setLayoutId(R.layout.fragment_layout).build()
    }
}