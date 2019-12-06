package org.softhk.gameout.ui.container.di
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import org.softhk.gameout.data.repository.remote.GameRepositoryAPI
import org.softhk.gameout.ui.container.GameActivityViewModel
import org.softhk.gameout.ui.container.GameActivityViewModelFactory
import org.softhk.gameout.ui.game.GameViewModelFactory
import org.softhk.gameout.utils.SharedPreferencesHelper
import javax.inject.Singleton

@Module
class GameActivityModule {

    @Singleton
    @Provides
    fun providerGameActivityViewModelFactory(sharedPreferences: SharedPreferencesHelper):GameActivityViewModelFactory{
        return GameActivityViewModelFactory(sharedPreferences)
    }
}