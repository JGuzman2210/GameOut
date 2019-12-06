package org.softhk.gameout.ui.game

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.softhk.gameout.data.model.Result

import org.softhk.gameout.data.repository.remote.GameRepositoryAPI
import org.softhk.gameout.utils.LayOutManagerSeleted
import org.softhk.gameout.utils.SPKey
import org.softhk.gameout.utils.SharedPreferencesHelper
import javax.inject.Inject

class GameViewModel constructor(var repositoryAPI: GameRepositoryAPI,
                                var sharedPreferencesHelper: SharedPreferencesHelper) : ViewModel() {

    @Inject
    lateinit var gameRespository: GameRepositoryAPI

    private lateinit var _repositoryAPI: GameRepositoryAPI
    private var _listOfGames = MutableLiveData<List<Result>>()


    init {
        _repositoryAPI = repositoryAPI
        _listOfGames = _repositoryAPI.getGamesMutableLiveData()
    }

    fun getGamesAPI(){
         var pageSize:Int = SPKey.SP_SHOW_DEFAULT_ITEMS_RECYCLER_VIEW
        sharedPreferencesHelper.contains(SPKey.SP_ELEMENT_SHOW_RECYCLER_VIEW).let {
            if(it){
                pageSize = sharedPreferencesHelper.get(SPKey.SP_ELEMENT_SHOW_RECYCLER_VIEW,SPKey.SP_SHOW_DEFAULT_ITEMS_RECYCLER_VIEW)
            }
        }
          _repositoryAPI.getGamesAPI(1,pageSize)

    }

    fun getGameLiveData():LiveData<List<Result>>{
        return this._listOfGames
    }

    fun loadLayoutConfigurationToRecyclerView():LayOutManagerSeleted{
        var layOutManagerSeleted: LayOutManagerSeleted? = null
        sharedPreferencesHelper.contains(SPKey.SP_LAYOUTMANAGER_RECYCLER_VIEW).let { result ->
            if (result) {
                val layoutManager = sharedPreferencesHelper
                    .get(
                        SPKey.SP_LAYOUTMANAGER_RECYCLER_VIEW,
                        null
                    ).let { layout ->
                        with(SPKey) {
                            when (layout) {
                                SP_GRID_LAYOUT_RECICLER_VIEW -> {
                                    layOutManagerSeleted = LayOutManagerSeleted.GRID_LAYOUT
                                }
                                SP_LINEAR_LAYOUT_RECYCLER_VIEW -> {
                                    layOutManagerSeleted = LayOutManagerSeleted.LINEAR_LAYOUT
                                }
                            }
                        }
                    }
            } else {
                layOutManagerSeleted = LayOutManagerSeleted.LINEAR_LAYOUT
            }
        }

        return layOutManagerSeleted!!
    }


}