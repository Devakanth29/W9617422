package uk.ac.tees.mad.w9617422.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import uk.ac.tees.mad.w9617422.data.remote.ApiService
import uk.ac.tees.mad.w9617422.data.remote.response.Review
import uk.ac.tees.mad.w9617422.util.FilmType
import java.io.IOException

class ReviewsSource(
    private val api: ApiService,
    private val filmId: Int,
    private val filmType: FilmType
) :
    PagingSource<Int, Review>() {
    override fun getRefreshKey(state: PagingState<Int, Review>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        return try {
            val nextPage = params.key ?: 1
            val filmReviews = api.getMovieReviews(
                page = nextPage,
                filmId = filmId,
                filmPath = if (filmType == FilmType.MOVIE) "movie" else "tv"
            )
            LoadResult.Page(
                data = filmReviews.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (filmReviews.results.isEmpty()) null else filmReviews.page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}