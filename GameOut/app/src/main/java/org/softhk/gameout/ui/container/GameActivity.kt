package org.softhk.gameout.ui.container

import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_gameout.*
import org.softhk.gameout.R
import org.softhk.gameout.di.GameOutApp
import org.softhk.gameout.ui.game.GamesFragment
import org.softhk.gameout.utils.GET_DATA
import org.softhk.gameout.utils.SharedPreferenceItem
import javax.inject.Inject

class GameActivity : AppCompatActivity() {

    //Drawer Navigation
    lateinit var toggle: ActionBarDrawerToggle

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    private var fragment: Fragment? = null

    fun addFrament(fragment: Fragment) {
        this.fragment = fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameout)
        setInjectActivity()


        toggle = ActionBarDrawerToggle(
            this, drawer_layout,
            R.string.open_nav_toggle, R.string.close_nav_toggle
        )

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        navigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mhome -> {
                    Toast.makeText(applicationContext, "HOME", Toast.LENGTH_LONG).show()
                    drawer_layout.closeDrawers()
                    true
                }
                R.id.msettings -> {
                    processSettings()
                    drawer_layout.closeDrawers()
                    true
                }
                R.id.mprofile -> {
                    Toast.makeText(applicationContext, "Profiles", Toast.LENGTH_LONG).show()
                    drawer_layout.closeDrawers()
                    true
                }
                else -> false
            }

        }


    }

    private fun processSettings() {
        //Created a dialog to show all settings of the App
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.settings_layout)

        var spinner = dialog.findViewById<Spinner>(R.id.spinner)
        var adapter: ArrayAdapter<Int> = ArrayAdapter<Int>(
            this, android.R.layout.simple_spinner_item,
            SharedPreferenceItem.listOfItemToShowRecyclerView
        )
        spinner.adapter = adapter

        if (sharedPreferences.contains(SharedPreferenceItem.SHARED_PREFERENCES_SPINNER_POSITION)) {
            var position: Int = sharedPreferences.getInt(
                SharedPreferenceItem.SHARED_PREFERENCES_SPINNER_POSITION,
                0
            )
            Log.e(GET_DATA, "GET Current position ${position}")
            spinner.setSelection(position)
        }

        var linear_layout_radio = dialog.findViewById<RadioButton>(R.id.linear_layout_radio)
        var grid_layout_radio = dialog.findViewById<RadioButton>(R.id.grid_layout_radio)


        if(sharedPreferences.contains(SharedPreferenceItem.SHARED_PREFERENCES_SHOW_LAYOUTMANAGER_RECYCLER_VIEW_KEY)){
            var layoutResult = sharedPreferences.getString(SharedPreferenceItem.SHARED_PREFERENCES_SHOW_LAYOUTMANAGER_RECYCLER_VIEW_KEY,
                SharedPreferenceItem.SHARED_PREFERENCES_LINEARLAYOUT)
            when (layoutResult) {
                SharedPreferenceItem.SHARED_PREFERENCES_GRIDLAYOUT -> {
                    grid_layout_radio.isChecked = true
                }
                SharedPreferenceItem.SHARED_PREFERENCES_LINEARLAYOUT -> {
                    linear_layout_radio.isChecked = true
                }
            }
        }


        var cancelSettingsButton = dialog.findViewById<Button>(R.id.CancelSettingsButton)
        cancelSettingsButton.setOnClickListener {
            dialog.dismiss()
        }


        var saveSettingsButton = dialog.findViewById<Button>(R.id.saveSettingsButton)

        saveSettingsButton.setOnClickListener {

            var spinnerItem: Int = spinner.selectedItem as Int
            var spinnerCurrenPosition: Int = spinner.selectedItemId.toInt()


            var editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt(
                SharedPreferenceItem.SHARED_PREFERECES_ELEMENT_TO_SHOW_RECYCLER,
                spinnerItem
            )
            editor.putInt(
                SharedPreferenceItem.SHARED_PREFERENCES_SPINNER_POSITION,
                spinnerCurrenPosition
            )

            //Validate LayOutManager

            if (linear_layout_radio.isChecked) {
                editor.putString(
                    SharedPreferenceItem.SHARED_PREFERENCES_SHOW_LAYOUTMANAGER_RECYCLER_VIEW_KEY,
                    SharedPreferenceItem.SHARED_PREFERENCES_LINEARLAYOUT
                )
            }else if (grid_layout_radio.isChecked){
                editor.putString(SharedPreferenceItem.SHARED_PREFERENCES_SHOW_LAYOUTMANAGER_RECYCLER_VIEW_KEY,
                    SharedPreferenceItem.SHARED_PREFERENCES_GRIDLAYOUT)
            }

            editor.commit()

            (fragment as GamesFragment).updateData()
            (fragment as GamesFragment).updateLayoutManagerRecyclerView()
            dialog.dismiss()

            Toast.makeText(this,"The configurations has been saved successfully",Toast.LENGTH_LONG).show()

        }

        dialog.show()
    }

    private fun setInjectActivity() {
        (application as GameOutApp).getGameOutComponent().inject(this)
    }


}


