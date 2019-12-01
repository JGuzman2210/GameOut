package org.softhk.gameout.di

import dagger.Component
import org.softhk.gameout.data.repository.di.RepositoryModule
import org.softhk.gameout.ui.container.GameActivity
import org.softhk.gameout.ui.game.GamesFragment
import org.softhk.gameout.ui.game.di.GameModule
import org.softhk.gameout.utils.di.UtilsModule
import javax.inject.Singleton

@Singleton
@Component(modules = [GameOutModule::class,RepositoryModule::class, GameModule::class,UtilsModule::class])
interface GameOutComponent {

    fun inject(gameActivity: GameActivity)

    fun Inject(gamesFragment: GamesFragment)


}