package org.demo.archtree

import archknife.annotation.ProvideModule
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@ProvideModule
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}