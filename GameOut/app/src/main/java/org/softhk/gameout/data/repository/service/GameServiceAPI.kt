package org.softhk.gameout.data.repository.service

import org.softhk.gameout.data.model.GameResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GameServiceAPI {

    @GET("games")
    fun getAllGame(@Query("page") page:Int, @Query("page_size") pagiSize:Int): Call<GameResponse>

}