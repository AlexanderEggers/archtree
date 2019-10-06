package archtree.helper

import android.content.Context
import archknife.annotation.ProvideModule
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This module provides the different helper classes from the helper-core artifact as singletons.
 * This module can only used with dagger-v2 or archknife.
 *
 * @since 1.0.0
 */
@Module
@ProvideModule
class HelperModule {

    @Singleton
    @Provides
    fun provideAccessibilityHelper(context: Context): AccessibilityHelper {
        return AccessibilityHelper(context)
    }

    @Singleton
    @Provides
    fun provideBitmapAccessor(context: Context): BitmapAccessor {
        return BitmapAccessor(context)
    }

    @Singleton
    @Provides
    fun provideConnectivityHelper(context: Context): ConnectivityHelper {
        return ConnectivityHelper(context)
    }

    @Singleton
    @Provides
    fun provideJsonHelper(context: Context, gson: Gson): JsonHelper {
        return JsonHelper(context, gson)
    }

    @Singleton
    @Provides
    fun provideResourceAccessor(context: Context): ResourceAccessor {
        return ResourceAccessor(context)
    }

    @Singleton
    @Provides
    fun provideTimeHelper(): TimeHelper {
        return TimeHelper()
    }
}