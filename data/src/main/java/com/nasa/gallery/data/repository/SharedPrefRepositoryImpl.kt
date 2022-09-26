package com.nasa.gallery.data.repository

import com.nasa.gallery.data.source.NasaDataSourceFactory
import com.nasa.gallery.domain.repository.SharedPrefRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SharedPrefRepositoryImpl @Inject constructor(private val dataSourceFactory: NasaDataSourceFactory) :
    SharedPrefRepository {
    override fun hasUserSeenOnBoarding(): Flow<Boolean> {
        return dataSourceFactory.preference().hasUserSeenOnBoarding()
    }

    override fun setUserHasSeenOnBoarding(value: Boolean): Flow<Unit> {
        return dataSourceFactory.preference().setUserHasSeenOnBoarding(value)
    }

    override fun isBookmarked(date: String): Flow<Boolean> {
        return dataSourceFactory.preference().isBookmarked(date)
    }

    override fun addBookmarkToPref(date: String): Flow<Unit> {
        return dataSourceFactory.preference().addBookmarkToSharedPref(date)
    }

    override fun removeBookmarkFromPref(date: String): Flow<Unit> {
        return dataSourceFactory.preference().removeBookmarkFromSharedPref(date)
    }
}
