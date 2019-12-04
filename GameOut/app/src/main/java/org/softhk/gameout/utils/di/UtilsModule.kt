package org.softhk.gameout.utils.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import org.softhk.gameout.utils.SharedPreferencesHelper
import javax.inject.Singleton

@Module
class UtilsModule {

    @Singleton
    @Provides
    fun providerSharedPreferences(context:Context) : SharedPreferences{
        return context.getSharedPreferences("GAMEOUT_KEY",Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providerSharedPreferencesHelper(sharedPreferences: SharedPreferences):SharedPreferencesHelper{
        return SharedPreferencesHelper(sharedPreferences)
    }
}