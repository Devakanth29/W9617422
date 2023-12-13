package uk.ac.tees.mad.w9617422.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.w9617422.data.remote.ApiService
import uk.ac.tees.mad.w9617422.model.Search
import uk.ac.tees.mad.w9617422.paging.SearchFilmSource
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: ApiService
) {
    fun multiSearch(searchParams: String, includeAdult: Boolean): Flow<PagingData<Search>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                SearchFilmSource(api = api, searchParams = searchParams, includeAdult)
            }
        ).flow
    }
}