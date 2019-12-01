package org.softhk.gameout.game


import androidx.lifecycle.MutableLiveData
import org.softhk.gameout.model.Result

interface GameRepository {


    interface GameRepositoryAPI{
        fun getGamesAPI(page:Int,page_size:Int)
        fun getGamesMutableLiveData():MutableLiveData<List<Result>>
    }

    interface GameRepositoryLocal{

    }
}
