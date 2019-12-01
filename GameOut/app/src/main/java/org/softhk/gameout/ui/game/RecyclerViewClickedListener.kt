package org.softhk.gameout.ui.game

import android.view.View
import org.softhk.gameout.data.model.Result

interface RecyclerViewClickedListener {
    fun viewHolderClicked(view:View,indexItem:Int,gameSelected: Result)
}