package com.nasa.gallery.ui

import com.nasa.gallery.data.repository.NasaRepositoryImpl
import com.nasa.gallery.data.repository.SharedPrefRepositoryImpl
import com.nasa.gallery.domain.repository.NasaRepository
import com.nasa.gallery.domain.repository.SharedPrefRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun localRepository(nasaRepositoryImpl: NasaRepositoryImpl): NasaRepository

    @Binds
    abstract fun sharedPrefRepository(sharedPrefRepositoryImpl: SharedPrefRepositoryImpl): SharedPrefRepository
}