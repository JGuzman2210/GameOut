package org.softhk.gameout.ui.gamedetails


import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.softhk.gameout.R
import org.softhk.gameout.data.entity.GameFavorite
import org.softhk.gameout.data.model.Result
import org.softhk.gameout.data.repository.local.GameFavoriteDAO
import org.softhk.gameout.di.GameOutApp
import org.softhk.gameout.utils.GET_DATA
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class GameDetailFragment : Fragment() {

    private lateinit var viewLayout: View
    private lateinit var gameImageView: ImageView
    private var gameSelected: Result? = null
    private var videoView: VideoView? = null
    private var playButtonVideo: Button? = null

    @Inject
    lateinit var gameFavoriteDAO: GameFavoriteDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLayout = inflater.inflate(R.layout.fragment_game_detail, container, false)
        var bundle = arguments!!.getParcelable("ResultSelected") as Result

        gameSelected = bundle

        setUpView(viewLayout)
        return viewLayout
    }

    private fun setUpView(v: View) {
        gameImageView = v.findViewById(R.id.game_image)
        videoView = v.findViewById(R.id.video_view)
        playButtonVideo = v.findViewById(R.id.play_button_video)

        Picasso.get().load(gameSelected!!.backgroundImage)
            .into(gameImageView)

        videoView!!.setVideoURI(Uri.parse(gameSelected!!.clip.clip))
        videoView!!.setMediaController(MediaController(activity))
        videoView!!.requestFocus()

        playButtonVideo!!.setOnClickListener {
            videoView!!.start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setInjectActivity()

    }

    private fun setInjectActivity() {
        (activity!!.applicationContext as GameOutApp).getGameOutComponent().Inject(this)
    }
}
