package com.nasa.gallery.di

import com.nasa.gallery.data.source.LocalDataSource
import com.nasa.gallery.data.source.PreferenceHelper
import com.nasa.gallery.local.source.LocalDataSourceImpl
import com.nasa.gallery.local.source.PreferenceHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * DataSourceModule for Dependency Injection
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun localDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    @Singleton
    abstract fun bindPreferenceHelper(preferenceHelperImpl: PreferenceHelperImpl): PreferenceHelper
}