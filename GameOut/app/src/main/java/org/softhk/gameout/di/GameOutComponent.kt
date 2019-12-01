package org.softhk.gameout.di

import dagger.Component
import org.softhk.gameout.ui.GameActivity
import org.softhk.gameout.game.GamesFragment
import org.softhk.gameout.game.di.GameModule
import org.softhk.gameout.utils.di.UtilsModule
import javax.inject.Singleton

@Singleton
@Component(modules = [GameOutModule::class,GameModule::class,UtilsModule::class])
interface GameOutComponent {

    fun inject(gameActivity: GameActivity)

    fun Inject(gamesFragment: GamesFragment)


}