package org.softhk.gameout.ui.game


import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import org.softhk.gameout.ui.container.GameActivity
import org.softhk.gameout.R
import org.softhk.gameout.di.GameOutApp
import org.softhk.gameout.data.model.Result
import org.softhk.gameout.data.repository.remote.GameRepositoryAPI
import org.softhk.gameout.utils.SPKey
import org.softhk.gameout.utils.SharedPreferencesHelper
import javax.inject.Inject

class GamesFragment : Fragment(),
    RecyclerViewClickedListener {

    @Inject
    lateinit var gameViewModelFactory: GameViewModelFactory

    @Inject
    lateinit var gameRespository: GameRepositoryAPI

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private var gameRecyclerView: RecyclerView? = null

    private var gameAdapter: GamesAdapter? = null

    private var gameViewModel: GameViewModel? = null

    private lateinit var viewLayout: View

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLayout = inflater.inflate(R.layout.fragment_games, container, false)


        setUpRecyclerView()

        return viewLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setInjectActivity()

        gameViewModel = ViewModelProviders.of(this, gameViewModelFactory)
            .get(GameViewModel::class.java)

        (activity as GameActivity).addFrament(this)
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    override fun viewHolderClicked(view: View, indexItem: Int, gameSelected: Result) {
        var bundle: Bundle = Bundle()
        bundle.putParcelable("ResultSelected", gameSelected)
        findNavController().navigate(R.id.action_gamesFragment_to_gameDetailFragment, bundle)

    }

    private fun setUpRecyclerView() {
        gameRecyclerView = viewLayout.findViewById(R.id.gameRecyclerView)

        sharedPreferencesHelper.contains(SPKey.SP_LAYOUTMANAGER_RECYCLER_VIEW).let { result ->
            if (result) {
                val layoutManager = sharedPreferencesHelper
                    .get(
                        SPKey.SP_LAYOUTMANAGER_RECYCLER_VIEW,
                        null
                    ).let { layout ->
                        with(SPKey) {
                            when (layout) {
                                SP_GRID_LAYOUT_RECICLER_VIEW -> {
                                    gameRecyclerView!!.layoutManager =
                                        GridLayoutManager(activity, 2)
                                }
                                SP_LINEAR_LAYOUT_RECYCLER_VIEW -> {
                                    gameRecyclerView!!.layoutManager =
                                        LinearLayoutManager(activity)
                                }
                            }
                        }
                    }
            } else {
                gameRecyclerView!!.layoutManager = LinearLayoutManager(activity)
            }

            gameAdapter = GamesAdapter(this)
            gameRecyclerView!!.adapter = gameAdapter
        }
    }

    fun updateData() {
        gameViewModel!!.getGamesAPI()

        gameViewModel!!.getGameLiveData().observe(this, Observer {
            gameAdapter!!.setGamesList(it)
        })
    }

    fun updateLayoutManagerRecyclerView() {
        setUpRecyclerView()
    }

    private fun setInjectActivity() {
        (activity!!.applicationContext as GameOutApp).getGameOutComponent().Inject(this)
    }
}
