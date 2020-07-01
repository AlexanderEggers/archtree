package archtree.activity

interface HasActivityBuilder {

    fun provideActivityResource(builder: ActivityBuilder): ActivityResource
}