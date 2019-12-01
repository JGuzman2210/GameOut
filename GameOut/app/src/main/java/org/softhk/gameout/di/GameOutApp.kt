package org.softhk.gameout.di

import android.app.Application
import android.util.Log

class GameOutApp : Application() {

     private lateinit var gameOutComponent:GameOutComponent

    override fun onCreate() {
        super.onCreate()
        gameOutComponent = DaggerGameOutComponent.builder()

            .gameOutModule(GameOutModule(this))

            .build()

    }

    fun getGameOutComponent():GameOutComponent = gameOutComponent
}