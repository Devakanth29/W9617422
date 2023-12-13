package uk.ac.tees.mad.w9617422.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import uk.ac.tees.mad.w9617422.data.remote.ApiService
import uk.ac.tees.mad.w9617422.model.Search
import java.io.IOException

class SearchFilmSource(
    private val api: ApiService,
    private val searchParams: String,
    private val includeAdult: Boolean
) : PagingSource<Int, Search>() {
    override fun getRefreshKey(state: PagingState<Int, Search>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        return try {
            val nextPage = params.key ?: 1
            val searchMovies = api.multiSearch(
                page = nextPage,
                searchParams = searchParams,
                includeAdult = includeAdult
            )
            LoadResult.Page(
                data = searchMovies.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (searchMovies.results.isEmpty()) null else searchMovies.page + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}