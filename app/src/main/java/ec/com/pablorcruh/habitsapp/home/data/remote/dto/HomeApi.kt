package ec.com.pablorcruh.habitsapp.home.data.remote.dto

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface HomeApi {

    companion object {
        const val BASE_URL = "https://daily-habits-4f2aa-default-rtdb.firebaseio.com/"
    }

    @GET("habits.json")
    suspend fun getAllHabits(): HabitResponse

    @PATCH("habits.json")
    suspend fun insertHabit(@Body habit: HabitResponse)

}