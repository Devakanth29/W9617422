package uk.ac.tees.mad.w9617422.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import uk.ac.tees.mad.w9617422.data.remote.ApiService
import uk.ac.tees.mad.w9617422.model.Film
import uk.ac.tees.mad.w9617422.util.FilmType
import java.io.IOException

class SimilarFilmSource(
    private val api: ApiService,
    val filmId: Int,
    private val filmType: FilmType
) :
    PagingSource<Int, Film>() {
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {
        return try {
            val nextPage = params.key ?: 1
            val similarMovies = if (filmType == FilmType.MOVIE) api.getSimilarMovies(
                page = nextPage, filmId = filmId
            )
            else api.getSimilarTvShows(page = nextPage, filmId = filmId)

            LoadResult.Page(
                data = similarMovies.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (similarMovies.results.isEmpty()) null else similarMovies.page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}