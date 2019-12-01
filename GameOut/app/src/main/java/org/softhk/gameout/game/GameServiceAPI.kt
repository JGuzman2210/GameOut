package org.softhk.gameout.game

import org.softhk.gameout.model.GameResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GameServiceAPI {

    //@Headers("x-rapidapi-key:9de123bfebmsh0693793b9de5540p1adc0bjsn2c9fb0f481a9")
    //https://api.rawg.io/api/games?page=1&page_size=2
    @GET("games")
    fun getAllGame(@Query("page") page:Int, @Query("page_size") pagiSize:Int): Call<GameResponse>

}