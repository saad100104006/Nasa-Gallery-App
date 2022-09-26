package com.nasa.gallery.domain.repository

import com.nasa.gallery.domain.model.NasaItemDomain
import kotlinx.coroutines.flow.Flow


interface NasaRepository {
    fun getData(): Flow<List<NasaItemDomain>>
    fun addBookmark(nasaItemDomain: NasaItemDomain): Flow<Unit>
    fun getAllBookmarks(): Flow<List<NasaItemDomain>>
    fun removeBookmark(nasaItemDomain: NasaItemDomain): Flow<Unit>
}