package ru.coin.alexwallet.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import ru.coin.alexwallet.data.NewsItem
import ru.coin.alexwallet.data.NewsRepository
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {
    private var searchResult: Flow<PagingData<NewsItem>>? = null
    fun searchPictures(): Flow<PagingData<NewsItem>> {
        return if (searchResult == null) {
            val newResult = repository.getSearchResultStream().cachedIn(viewModelScope)
            searchResult = newResult
            newResult
        } else {
            return searchResult as Flow<PagingData<NewsItem>>
        }
    }
}
