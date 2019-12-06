package org.softhk.gameout.ui.container

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.softhk.gameout.data.repository.remote.GameRepositoryAPI
import org.softhk.gameout.utils.SharedPreferencesHelper
import javax.inject.Inject

class GameActivityViewModelFactory @Inject constructor(
    var sharedPreferences: SharedPreferencesHelper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

      return  with(modelClass) {
            when {
                isAssignableFrom(GameActivityViewModel::class.java) ->
                    GameActivityViewModel(sharedPreferences)

                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }

        } as T
    }
}