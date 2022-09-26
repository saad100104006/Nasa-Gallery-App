package com.nasa.gallery.data.repository

import com.nasa.gallery.data.mapper.NasaItemMapper
import com.nasa.gallery.data.source.NasaDataSourceFactory
import com.nasa.gallery.domain.model.NasaItemDomain
import com.nasa.gallery.domain.repository.NasaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class NasaRepositoryImpl @Inject constructor(
    private val nasaDataSourceFactory: NasaDataSourceFactory,
    private val nasaItemMapper: NasaItemMapper
) : NasaRepository {

    override fun getData(): Flow<List<NasaItemDomain>> {
        return nasaDataSourceFactory.local().getData().map { items ->
            items.map { nasaItemMapper.mapDataToDomain(it) }
        }
    }

    override fun addBookmark(nasaItemDomain: NasaItemDomain): Flow<Unit> {
        return nasaDataSourceFactory.local()
            .addBookmark(nasaItemMapper.mapDomainToData(nasaItemDomain))
    }

    override fun getAllBookmarks(): Flow<List<NasaItemDomain>> {
        return nasaDataSourceFactory.local().getAllBookmarks().map { bookmarks ->
            bookmarks.map { nasaItemMapper.mapDataToDomain(it) }
        }
    }

    override fun removeBookmark(nasaItemDomain: NasaItemDomain): Flow<Unit> {
        return nasaDataSourceFactory.local()
            .removeBookmark(nasaItemMapper.mapDomainToData(nasaItemDomain))
    }
}