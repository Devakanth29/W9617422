package uk.ac.tees.mad.w9617422.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.w9617422.data.remote.response.Review
import uk.ac.tees.mad.w9617422.data.repository.ReviewsRepository
import uk.ac.tees.mad.w9617422.util.FilmType
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(private val repo: ReviewsRepository) : ViewModel() {

    private val _filmReviews = mutableStateOf<Flow<PagingData<Review>>>(emptyFlow())
    val filmReviews: State<Flow<PagingData<Review>>> = _filmReviews

    fun getFilmReview(filmId: Int, filmType: FilmType) {
        viewModelScope.launch {
            _filmReviews.value = repo.getFilmReviews(filmId = filmId, filmType).cachedIn(viewModelScope)
        }
    }
}
