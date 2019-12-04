package org.softhk.gameout.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.softhk.gameout.data.repository.remote.GameRepositoryAPI
import org.softhk.gameout.utils.SharedPreferencesHelper
import javax.inject.Inject

class GameViewModelFactory @Inject constructor(
    var respositoryAPI: GameRepositoryAPI,
    var sharedPreferences: SharedPreferencesHelper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

      return  with(modelClass) {
            when {
                isAssignableFrom(GameViewModel::class.java) ->
                    GameViewModel(respositoryAPI, sharedPreferences)

                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }

        } as T
    }
}