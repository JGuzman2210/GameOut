package org.softhk.gameout.ui.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.softhk.gameout.R
import org.softhk.gameout.data.model.Result
import org.softhk.gameout.di.GameOutApp
import org.softhk.gameout.ui.container.GameActivity
import org.softhk.gameout.utils.LayOutManagerSeleted
import org.softhk.gameout.utils.SPKey
import javax.inject.Inject

class GamesFragment : Fragment(),
    RecyclerViewClickedListener {

    @Inject
    lateinit var gameViewModelFactory: GameViewModelFactory

    private lateinit var gameRecyclerView: RecyclerView

    private lateinit var gameAdapter: GamesAdapter

    private lateinit var gameViewModel: GameViewModel

    private lateinit var viewLayout: View


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

        when (gameViewModel.loadLayoutConfigurationToRecyclerView()) {

            LayOutManagerSeleted.LINEAR_LAYOUT -> {
               gameRecyclerView.layoutManager = LinearLayoutManager(activity)
            }
            LayOutManagerSeleted.GRID_LAYOUT -> {
                gameRecyclerView.layoutManager = GridLayoutManager(activity,SPKey.SP_DEFAULT_COLUM_GRID_LAYOUT_MANAGER)
            }
        }

        gameAdapter = GamesAdapter(this)
        gameRecyclerView.adapter = gameAdapter
    }

    fun updateData() {
        gameViewModel.getGamesAPI()

        gameViewModel.getGameLiveData().observe(this, Observer {
            gameAdapter.setGamesList(it)
        })
    }

    fun updateLayoutManagerRecyclerView() {
        setUpRecyclerView()
    }

    private fun setInjectActivity() {
        (activity!!.applicationContext as GameOutApp).getGameOutComponent().Inject(this)
    }
}
