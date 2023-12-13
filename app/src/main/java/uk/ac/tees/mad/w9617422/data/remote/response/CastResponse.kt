package uk.ac.tees.mad.w9617422.data.remote.response

import uk.ac.tees.mad.w9617422.model.Cast
import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val castResult: List<Cast>
)
