package org.softhk.gameout.ui.container

import androidx.core.content.pm.ShortcutInfoCompatSaver
import androidx.lifecycle.ViewModel
import org.softhk.gameout.utils.LayOutManagerSeleted
import org.softhk.gameout.utils.SPKey
import org.softhk.gameout.utils.SharedPreferencesHelper
import javax.inject.Inject


class GameActivityViewModel @Inject constructor(
    var sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {





    fun loadValueToSpinnerSettingInDialogView():Int{
        var positionSpinner: Int = SPKey.SP_SPINNER_DEFAULT_POSITION
        sharedPreferencesHelper.contains(SPKey.SP_SPINNER_POSITION).let {
            if (it) {
                positionSpinner = sharedPreferencesHelper.get(SPKey.SP_SPINNER_POSITION, SPKey.SP_SPINNER_DEFAULT_POSITION)
            }
        }
        return positionSpinner
    }

    fun loadSelectedLayOutManagerSettingInDialog():LayOutManagerSeleted {
        var layOutManagerSeleted: LayOutManagerSeleted? = null
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
                                    layOutManagerSeleted = LayOutManagerSeleted.GRID_LAYOUT
                                }
                                SP_LINEAR_LAYOUT_RECYCLER_VIEW -> {
                                    layOutManagerSeleted = LayOutManagerSeleted.LINEAR_LAYOUT
                                }
                            }
                        }
                    }
            }
        }
        return layOutManagerSeleted!!
    }

    fun saveSettingSpinnerValue(spinnerItem:Int,spinnerPosition:Int){

        sharedPreferencesHelper.put(SPKey.SP_ELEMENT_SHOW_RECYCLER_VIEW, spinnerItem)
        sharedPreferencesHelper.put(SPKey.SP_SPINNER_POSITION, spinnerPosition)
    }

    fun saveSettingRecyclerLayOutManager(layoutType:String){
        sharedPreferencesHelper.put(
            SPKey.SP_LAYOUTMANAGER_RECYCLER_VIEW,
            layoutType
        )
    }
}