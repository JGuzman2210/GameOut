package org.softhk.gameout.data.repository.remote


import androidx.lifecycle.MutableLiveData
import org.softhk.gameout.data.model.Result


interface GameRepositoryAPI {
    fun getGamesAPI(page: Int, pageSize: Int)
    fun getGamesMutableLiveData(): MutableLiveData<List<Result>>
}

