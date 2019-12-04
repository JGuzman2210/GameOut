package org.softhk.gameout.ui.game.di
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import org.softhk.gameout.data.repository.remote.GameRepositoryAPI
import org.softhk.gameout.ui.game.GameViewModelFactory
import org.softhk.gameout.utils.SharedPreferencesHelper
import javax.inject.Singleton

@Module
class GameModule {

    @Singleton
    @Provides
    fun providergetViewModelFactory(respositoryAPI: GameRepositoryAPI, sharedPreferences: SharedPreferencesHelper):GameViewModelFactory{
        return GameViewModelFactory(respositoryAPI,sharedPreferences)
    }
}