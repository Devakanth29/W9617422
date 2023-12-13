package uk.ac.tees.mad.w9617422.data.remote.response

import uk.ac.tees.mad.w9617422.model.Genre
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
)