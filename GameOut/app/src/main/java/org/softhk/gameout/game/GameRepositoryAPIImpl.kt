package org.softhk.gameout.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.softhk.gameout.model.GameResponse
import org.softhk.gameout.model.Result
import org.softhk.gameout.utils.GET_DATA
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response

class GameRepositoryAPIImpl @Inject constructor(
                            var gameServiceAPI:GameServiceAPI)
                            : GameRepository.GameRepositoryAPI{
    private var _listOfGames = MutableLiveData<List<Result>>()


    override fun getGamesAPI(page: Int, page_size: Int){

        gameServiceAPI.getAllGame(page,page_size).enqueue(object : Callback<GameResponse>{
            override fun onFailure(call: Call<GameResponse>, t: Throwable) {
               Log.e(GET_DATA,t.message.toString())
            }

            override fun onResponse(call: Call<GameResponse>, response: Response<GameResponse>) {
               if(response.body()!!.results != null){
                   _listOfGames.value = response.body()!!.results
               }
            }
        })
    }

    override fun getGamesMutableLiveData(): MutableLiveData<List<Result>> {

        return _listOfGames
    }
}