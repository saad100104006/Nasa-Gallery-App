package com.nasa.gallery.domain.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainCoroutineDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IOCoroutineDispatcher
