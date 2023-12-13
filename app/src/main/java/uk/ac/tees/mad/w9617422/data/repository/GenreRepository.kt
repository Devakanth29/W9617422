package uk.ac.tees.mad.w9617422.data.repository

import uk.ac.tees.mad.w9617422.data.remote.ApiService
import uk.ac.tees.mad.w9617422.data.remote.response.GenreResponse
import uk.ac.tees.mad.w9617422.util.FilmType
import uk.ac.tees.mad.w9617422.util.Resource
import java.lang.Exception
import javax.inject.Inject

class GenreRepository @Inject constructor(private val api: ApiService) {
    suspend fun getMoviesGenre(filmType: FilmType): Resource<GenreResponse> {
        val response = try {
            if (filmType == FilmType.MOVIE) api.getMovieGenres() else api.getTvShowGenres()
        } catch (e: Exception){
            return Resource.Error("Unknown error occurred!")
        }
        return Resource.Success(response)
    }
}