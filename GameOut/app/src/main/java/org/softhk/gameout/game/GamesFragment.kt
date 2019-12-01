package org.softhk.gameout.game


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
import kotlinx.android.synthetic.main.game_item.view.*
import org.softhk.gameout.ui.GameActivity
import org.softhk.gameout.R
import org.softhk.gameout.di.GameOutApp
import org.softhk.gameout.game.adapter.GamesAdapter
import org.softhk.gameout.game.adapter.RecyclerViewClickedListener
import org.softhk.gameout.model.Result
import org.softhk.gameout.utils.SharedPreferenceItem
import java.io.Serializable
import javax.inject.Inject

class GamesFragment : Fragment(), RecyclerViewClickedListener {

    @Inject
    lateinit var gameViewModelFactory: GameViewModelFactory

    @Inject
    lateinit var gameRespository: GameRepository.GameRepositoryAPI

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    private var gameRecyclerView: RecyclerView? = null

    private var gameAdapter: GamesAdapter? = null

    private var gameViewModel: GameViewModel? = null

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

    override fun viewHolderClicked(view:View,indexItem:Int,gameSelected: Result) {


        var bundle: Bundle = Bundle()
        bundle.putSerializable("ResultSelected",Gson().toJson(gameSelected))
        findNavController().navigate(R.id.action_gamesFragment_to_gameDetailFragment, bundle)


    }

    private fun setUpRecyclerView() {
        gameRecyclerView = viewLayout.findViewById(R.id.gameRecyclerView)

        if (sharedPreferences.contains(SharedPreferenceItem.SHARED_PREFERENCES_SHOW_LAYOUTMANAGER_RECYCLER_VIEW_KEY)) {

            var layoutResult = sharedPreferences.getString(
                SharedPreferenceItem.SHARED_PREFERENCES_SHOW_LAYOUTMANAGER_RECYCLER_VIEW_KEY,
                SharedPreferenceItem.SHARED_PREFERENCES_LINEARLAYOUT
            )
            when (layoutResult) {
                SharedPreferenceItem.SHARED_PREFERENCES_GRIDLAYOUT -> {
                    gameRecyclerView!!.layoutManager = GridLayoutManager(activity, 2)
                }
                SharedPreferenceItem.SHARED_PREFERENCES_LINEARLAYOUT -> {
                    gameRecyclerView!!.layoutManager = LinearLayoutManager(activity)
                }
            }

        } else {
            //Set LinearLayoutManager by Default
            gameRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        }



        gameAdapter = GamesAdapter(this)
        gameRecyclerView!!.adapter = gameAdapter
    }

    fun updateData() {
        gameViewModel!!.getGamesAPI()

        gameViewModel!!.getGameLiveData().observe(this, Observer {
            gameAdapter!!.setGamesList(it)
        })
    }

    fun updateLayoutManagerRecyclerView(){
        setUpRecyclerView()
    }

    private fun setInjectActivity() {
        (activity!!.applicationContext as GameOutApp).getGameOutComponent().Inject(this)
    }


}
