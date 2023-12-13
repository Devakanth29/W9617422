package uk.ac.tees.mad.w9617422.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.w9617422.data.remote.ApiService
import uk.ac.tees.mad.w9617422.data.remote.response.Review
import uk.ac.tees.mad.w9617422.paging.ReviewsSource
import uk.ac.tees.mad.w9617422.util.FilmType
import javax.inject.Inject

class ReviewsRepository @Inject constructor(private val api: ApiService) {

    fun getFilmReviews(filmId: Int, filmType: FilmType): Flow<PagingData<Review>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                ReviewsSource(api = api, filmId = filmId, filmType = filmType)
            }
        ).flow
    }
}
