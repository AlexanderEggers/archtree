package archtree.fragment

interface HasFragmentBuilder {

    fun provideFragmentResource(builder: FragmentBuilder): FragmentResource
}