package org.softhk.gameout.ui.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.softhk.gameout.R
import org.softhk.gameout.data.model.Result

class GamesAdapter constructor(var recyclerViewClicked: RecyclerViewClickedListener) :
    RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    private var _listOfGames: ArrayList<Result> = ArrayList<Result>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_item, parent, false)
        return GameViewHolder(view, recyclerViewClicked)
    }

    override fun getItemCount(): Int {
        return _listOfGames.size
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        var currentGame = _listOfGames.get(position)
        holder.bind(currentGame)
    }


    fun setGamesList(listOfGames: List<Result>) {
        this._listOfGames.clear()
        this._listOfGames.addAll(listOfGames)
        notifyDataSetChanged()
    }

    //ViewHolder
    inner class GameViewHolder constructor(
        itemVIew: View,
        var recyclerViewClicked: RecyclerViewClickedListener
    ) : RecyclerView.ViewHolder(itemVIew), View.OnClickListener {

        lateinit var circleImage: CircleImageView
        lateinit var nameTextView: TextView
        lateinit var releasedTextView:TextView
        lateinit var ratingBar:RatingBar
        lateinit var circleImageViewPc:CircleImageView
        lateinit var circleImageViewXbox:CircleImageView
        lateinit var circleImageViewPlaystation:CircleImageView

        var listener: RecyclerViewClickedListener? = null

        init {
            circleImage = itemVIew.findViewById(R.id.circle_image)
            nameTextView = itemVIew.findViewById(R.id.name_textView)
            releasedTextView = itemVIew.findViewById(R.id.releasedTextView)
            ratingBar = itemVIew.findViewById(R.id.ratingBar)

        }

        fun bind(currentGame: Result) {
            Picasso.get().load(currentGame.backgroundImage)
                .into(circleImage)
            nameTextView.text = currentGame.name
            releasedTextView.text = currentGame.released
            ratingBar.rating = currentGame.rating.toFloat()






            listener = recyclerViewClicked
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            var gameSelected = _listOfGames.get(adapterPosition)
            recyclerViewClicked.viewHolderClicked(v!!,adapterPosition,gameSelected)
        }

    }
}