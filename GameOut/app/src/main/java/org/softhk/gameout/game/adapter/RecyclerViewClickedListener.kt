package org.softhk.gameout.game.adapter

import android.view.View
import org.softhk.gameout.model.Result

interface RecyclerViewClickedListener {
    fun viewHolderClicked(view:View,indexItem:Int,gameSelected:Result)
}