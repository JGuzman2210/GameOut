package org.softhk.gameout.utils

import android.content.SharedPreferences
import javax.inject.Inject

object SPKey    {
    val SP_ELEMENT_SHOW_RECYCLER_VIEW = "ELEMENT_TO_SHOW"
    val SP_SPINNER_POSITION = "SPINNER_POSITION"
    val SP_SPINNER_DEFAULT_POSITION = 0
    val SP_SHOW_DEFAULT_ITEMS_RECYCLER_VIEW = 5
    val SP_DEFAULT_COLUM_GRID_LAYOUT_MANAGER = 2
    val SP_LAYOUTMANAGER_RECYCLER_VIEW = "SHOW_LAYOUT_MANAGER"
    val SP_LINEAR_LAYOUT_RECYCLER_VIEW = "LINEAR_LAYOUT"
    val SP_GRID_LAYOUT_RECICLER_VIEW = "GRID_LAYOUT"
    var listOfItemToShowRecyclerView = arrayListOf<Int>(5,10,20,30,40)
}

enum class LayOutManagerSeleted{
    LINEAR_LAYOUT,GRID_LAYOUT
}

class SharedPreferencesHelper @Inject constructor(var sharedPreferences: SharedPreferences) {


    fun put(key:String,value:String):Unit{

        sharedPreferences.edit().putString(key,value).apply()
    }

    fun put(key:String,value:Int):Unit{
        sharedPreferences.edit().putInt(key,value).apply()
    }

    fun get(key:String,defaultValue:String?):String{
        return sharedPreferences.getString(key,defaultValue)
    }

    fun get(key:String,defaultValue:Int):Int{
        return sharedPreferences.getInt(key,defaultValue)
    }

    fun contains(key:String):Boolean{
        return sharedPreferences.contains(key)
    }

}