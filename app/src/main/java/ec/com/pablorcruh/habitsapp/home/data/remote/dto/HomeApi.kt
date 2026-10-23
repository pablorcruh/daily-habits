package ec.com.pablorcruh.habitsapp.home.data.remote.dto

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface HomeApi {

    companion object {
        const val BASE_URL = "http://10.0.2.2:8080"
    }

    @GET("habits.json")
    suspend fun getAllHabits(): HabitResponse

    @PATCH("habits.json")
    suspend fun insertHabit(@Body habit: HabitResponse)

}