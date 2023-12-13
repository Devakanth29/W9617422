package uk.ac.tees.mad.w9617422.data.repository


import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.w9617422.data.preferences.UserPreferences
import javax.inject.Inject

class PrefsRepository @Inject constructor(
    private val userPreferences: UserPreferences
) {
    suspend fun updateIncludeAdult(includeAdult: Boolean){
        userPreferences.updateIncludeAdult(includeAdult)
    }
    fun readIncludeAdult(): Flow<Boolean?> = userPreferences.includeAdultFlow
}