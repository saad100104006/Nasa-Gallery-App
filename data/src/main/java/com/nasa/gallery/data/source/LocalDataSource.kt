package com.nasa.gallery.data.source

import com.nasa.gallery.data.model.NasaItemData
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getData(): Flow<List<NasaItemData>>

    fun addBookmark(nasaItemData: NasaItemData): Flow<Unit>

    fun getAllBookmarks(): Flow<List<NasaItemData>>

    fun removeBookmark(nasaItemData: NasaItemData): Flow<Unit>
}