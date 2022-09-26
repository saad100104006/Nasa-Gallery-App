package com.nasa.gallery.local.source

import android.content.SharedPreferences
import com.nasa.gallery.data.source.PreferenceHelper
import com.nasa.gallery.local.util.LocalConstants.ON_BOARDING_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(private val pref: SharedPreferences): PreferenceHelper {

    override fun hasUserSeenOnBoarding(): Flow<Boolean> {
        return flow { emit(pref.getBoolean(ON_BOARDING_KEY, false)) }
    }

    override fun setUserHasSeenOnBoarding(value: Boolean) : Flow<Unit> {
        return flow { emit(pref.edit().putBoolean(ON_BOARDING_KEY, value).apply()) }
    }

    override fun addBookmarkToSharedPref(date: String): Flow<Unit> {
        return flow { emit(pref.edit().putBoolean(date, true).apply()) }
    }

    override fun removeBookmarkFromSharedPref(date: String): Flow<Unit> {
        return flow { emit(pref.edit().putBoolean(date, false).apply()) }
    }

    override fun isBookmarked(date: String): Flow<Boolean> {
        return flow { emit(pref.getBoolean(date, false)) }
    }
}
