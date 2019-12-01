package org.softhk.gameout.ui.game

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.softhk.gameout.data.repository.GameRepositoryAPI
import javax.inject.Inject

class GameViewModelFactory @Inject constructor(
    var respositoryAPI: GameRepositoryAPI,
    var sharedPreferences: SharedPreferences
) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {


        var vm = GameViewModel(
            respositoryAPI,
            sharedPreferences
        )

        return vm as T

    }
}