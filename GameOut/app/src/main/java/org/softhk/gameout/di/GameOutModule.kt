package org.softhk.gameout.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GameOutModule constructor(var application: Application) {

    @Singleton
    @Provides
    fun providerContext():Context = application.applicationContext





}