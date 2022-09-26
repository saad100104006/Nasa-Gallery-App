package com.nasa.gallery.di

import android.content.Context
import coil.ImageLoader
import coil.util.CoilUtils
import com.nasa.gallery.domain.di.IOCoroutineDispatcher
import com.nasa.gallery.domain.di.MainCoroutineDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NasaModule {

    companion object {
        private const val TIMEOUT_CALL: Long = 20L
        private const val TIMEOUT_READ: Long = 20L
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(TIMEOUT_CALL, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .cache(CoilUtils.createDefaultCache(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @IOCoroutineDispatcher
    fun provideIOCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @MainCoroutineDispatcher
    fun provideMainCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main.immediate
    }
}
