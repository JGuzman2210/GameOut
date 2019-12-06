package org.softhk.gameout.di

import dagger.Component
import org.softhk.gameout.data.repository.di.RepositoryModule
import org.softhk.gameout.data.repository.local.datasource.di.DataSouceModule
import org.softhk.gameout.ui.container.GameActivity
import org.softhk.gameout.ui.container.di.GameActivityModule
import org.softhk.gameout.ui.game.GamesFragment
import org.softhk.gameout.ui.game.di.GameModule
import org.softhk.gameout.ui.gamedetails.GameDetailFragment
import org.softhk.gameout.utils.di.UtilsModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    GameOutModule::class,
    RepositoryModule::class,
    GameModule::class,
    DataSouceModule::class,
    UtilsModule::class,
    GameActivityModule::class])
interface GameOutComponent {

    fun inject(gameActivity: GameActivity)

    fun Inject(gamesFragment: GamesFragment)

    fun Inject(gameDetailFragment: GameDetailFragment)


}