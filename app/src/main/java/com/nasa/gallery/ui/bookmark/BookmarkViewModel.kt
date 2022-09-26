package com.nasa.gallery.ui.bookmark

import androidx.lifecycle.*
import com.nasa.gallery.domain.usecase.*
import com.nasa.gallery.mapper.NasaItemMapper
import com.nasa.gallery.model.NasaItem
import com.nasa.gallery.util.Event
import com.nasa.gallery.util.Message
import com.nasa.gallery.util.Result
import com.nasa.gallery.util.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getAllBookmarksUseCase: GetAllBookmarksUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
    private val addBookmarkToSharedPrefUseCase: AddBookmarkToSharedPrefUseCase,
    private val removeBookmarkFromSharedPrefUseCase: RemoveBookmarkFromSharedPrefUseCase,
    private val isBookmarkUseCase: IsBookmarkUseCase,
    private val mapper: NasaItemMapper
) :
    ViewModel() {

    private val _allBookmarks = MutableLiveData<Result<List<NasaItem>>>()
    val allBookmarks get() = _allBookmarks.asLiveData()

    private val _bookmarkMessage = MutableLiveData<Event<Message>>()
    val bookmarkMessage get() = _bookmarkMessage.asLiveData()

    init {
        getAllBookmarks()
    }

    private fun getAllBookmarks() {
        viewModelScope.launch {
            getAllBookmarksUseCase()
                .catch { }
                .collect {
                    _allBookmarks.postValue(Result.Success(it.map { item ->
                        mapper.mapDomainToAppLayer(item)
                    }))
                }
        }
    }

    fun addBookmark(nasaItem: NasaItem) {
        viewModelScope.launch {
            addBookmarkUseCase(mapper.mapAppToDomainLayer(nasaItem))
                .catch { }
                .onCompletion { _bookmarkMessage.postValue(Event(Message("Added to bookmarks", false))) }
                .collect { addBookmarkToSharedPrefUseCase(nasaItem.date).collect() }
        }
    }

    fun removeBookmark(nasaItem: NasaItem) {
        viewModelScope.launch {
            removeBookmarkUseCase(mapper.mapAppToDomainLayer(nasaItem))
                .catch { }
                .onCompletion { _bookmarkMessage.postValue(Event(Message("Removed from bookmarks", true))) }
                .collect { removeBookmarkFromSharedPrefUseCase(nasaItem.date).collect() }
        }
    }

    fun isBookmark(nasaItem: NasaItem): LiveData<Boolean> {
        return liveData {
            isBookmarkUseCase(nasaItem.date).collect { emit(it) }
        }
    }
}