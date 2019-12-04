package org.softhk.gameout.ui.game

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.softhk.gameout.data.model.Result

import org.softhk.gameout.data.repository.remote.GameRepositoryAPI
import org.softhk.gameout.utils.SPKey
import org.softhk.gameout.utils.SharedPreferencesHelper
import javax.inject.Inject

class GameViewModel constructor(var repositoryAPI: GameRepositoryAPI, var sharedPreferences: SharedPreferencesHelper) : ViewModel() {

    @Inject
    lateinit var gameRespository: GameRepositoryAPI

    private lateinit var _repositoryAPI: GameRepositoryAPI
    private var _listOfGames = MutableLiveData<List<Result>>()


    init {
        _repositoryAPI = repositoryAPI
        _listOfGames = _repositoryAPI!!.getGamesMutableLiveData()
    }


    fun getGamesAPI(){
         var pageSize:Int = SPKey.SP_SHOW_DEFAULT_ITEMS_RECYCLER_VIEW
        sharedPreferences.contains(SPKey.SP_ELEMENT_SHOW_RECYCLER_VIEW).let {
            if(it){
                pageSize = sharedPreferences.get(SPKey.SP_ELEMENT_SHOW_RECYCLER_VIEW,SPKey.SP_SHOW_DEFAULT_ITEMS_RECYCLER_VIEW)
            }
        }
          _repositoryAPI!!.getGamesAPI(1,pageSize)

    }

    fun getGameLiveData():LiveData<List<Result>>{
        return this._listOfGames
    }



}