package com.nasa.gallery.data.source

import javax.inject.Inject


class NasaDataSourceFactory @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val preferenceDataSource: PreferenceHelper
) {
    fun local() = localDataSource
    fun preference() = preferenceDataSource
}
