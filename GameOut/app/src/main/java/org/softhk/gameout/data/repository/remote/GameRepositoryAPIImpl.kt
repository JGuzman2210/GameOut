package org.softhk.gameout.data.repository.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.softhk.gameout.data.model.GameResponse
import org.softhk.gameout.data.model.Result
import org.softhk.gameout.data.repository.GameRepositoryAPI
import org.softhk.gameout.data.repository.service.GameServiceAPI
import org.softhk.gameout.utils.GET_DATA
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response

class GameRepositoryAPIImpl @Inject constructor(
                            var gameServiceAPI: GameServiceAPI
)
                            :
    GameRepositoryAPI {
    private var _listOfGames = MutableLiveData<List<Result>>()


    override fun getGamesAPI(page: Int, pageSize: Int){

        gameServiceAPI.getAllGame(page,pageSize).enqueue(object : Callback<GameResponse>{
            override fun onFailure(call: Call<GameResponse>, t: Throwable) {
               Log.e(GET_DATA,t.message.toString())
            }

            override fun onResponse(call: Call<GameResponse>, response: Response<GameResponse>) {
               if(response.body()!!){
                   _listOfGames.value = response.body()!!.results
               }
            }
        })
    }

    override fun getGamesMutableLiveData(): MutableLiveData<List<Result>> {

        return _listOfGames
    }
}