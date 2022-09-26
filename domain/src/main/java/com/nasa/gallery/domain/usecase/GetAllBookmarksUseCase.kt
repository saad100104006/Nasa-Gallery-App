package com.nasa.gallery.domain.usecase

import com.nasa.gallery.domain.di.IOCoroutineDispatcher
import com.nasa.gallery.domain.model.NasaItemDomain
import com.nasa.gallery.domain.repository.NasaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllBookmarksUseCase @Inject constructor(
    private val nasaRepository: NasaRepository,
    @IOCoroutineDispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<NasaItemDomain>> {
        return nasaRepository.getAllBookmarks().flowOn(dispatcher)
    }
}