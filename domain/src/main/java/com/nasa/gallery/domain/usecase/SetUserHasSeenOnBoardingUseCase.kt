package com.nasa.gallery.domain.usecase

import com.nasa.gallery.domain.di.MainCoroutineDispatcher
import com.nasa.gallery.domain.repository.SharedPrefRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SetUserHasSeenOnBoardingUseCase @Inject constructor(
    private val prefRepository: SharedPrefRepository,
    @MainCoroutineDispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(value: Boolean): Flow<Unit> {
        return prefRepository.setUserHasSeenOnBoarding(value).flowOn(dispatcher)
    }
}