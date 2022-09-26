package com.nasa.gallery.ui.details

import androidx.lifecycle.*
import com.nasa.gallery.domain.usecase.*
import com.nasa.gallery.mapper.NasaItemMapper
import com.nasa.gallery.model.NasaItem
import com.nasa.gallery.util.Event
import com.nasa.gallery.util.Result
import com.nasa.gallery.util.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PictureDetailsViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val getAllBookmarksUseCase: GetAllBookmarksUseCase,
    private val hasUserSeenOnBoardingUseCase: HasUserSeenOnBoardingUseCase,
    private val setUserHasSeenOnBoardingUseCase: SetUserHasSeenOnBoardingUseCase,
    private val mapper: NasaItemMapper
) : ViewModel() {

    private val _pictureList = MutableLiveData<Result<List<NasaItem>>>()
    val pictureList get() = _pictureList.asLiveData()

    private val _adapterPosition = MutableLiveData<Event<Int>>()
    val adapterPosition get() = _adapterPosition.asLiveData()

    fun getPictureList() {
        viewModelScope.launch {
            getDataUseCase()
                .catch { _pictureList.postValue(Result.Error(it)) }
                .collect { items ->
                    _pictureList.postValue(Result.Success(items.reversed().map {
                        mapper.mapDomainToAppLayer(it)
                    }))
                }
        }
    }

    fun getAllBookmarks() {
        viewModelScope.launch {
            getAllBookmarksUseCase()
                .catch { _pictureList.postValue(Result.Error(it)) }
                .collect {
                    _pictureList.postValue(Result.Success(it.map { item ->
                        mapper.mapDomainToAppLayer(item)
                    }))
                }
        }
    }

    val hasUserSeenOnBoarding: LiveData<Boolean> = liveData {
        hasUserSeenOnBoardingUseCase()
            .catch { }
            .collect { emit(it) }
    }

    fun setUserHasSeenOnBoarding(value: Boolean) {
        viewModelScope.launch {
            setUserHasSeenOnBoardingUseCase(value)
                .catch {}
                .collect()
        }
    }

    fun setAdapterPosition(position: Int) {
        _adapterPosition.value = Event(position)
    }
}