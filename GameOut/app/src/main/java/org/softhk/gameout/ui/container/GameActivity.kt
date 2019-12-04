package org.softhk.gameout.ui.container

import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import android.widget.Button
import android.widget.Toast
import android.widget.RadioGroup
import android.widget.RadioButton
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import org.softhk.gameout.R
import org.softhk.gameout.di.GameOutApp
import org.softhk.gameout.ui.game.GamesFragment
import org.softhk.gameout.utils.SPKey
import org.softhk.gameout.utils.SharedPreferencesHelper
import javax.inject.Inject

class GameActivity : AppCompatActivity(),View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper


    private var fragment: Fragment? = null

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navigation: NavigationView

    private lateinit var dialog: Dialog
    private lateinit var spinner: Spinner
    private lateinit var adapter: ArrayAdapter<Int>
    private lateinit var radioGroupLayoutManager: RadioGroup
    private lateinit var radioButtonLinearLayout:RadioButton
    private lateinit var radioButtonGridLayout:RadioButton
    private lateinit var cancelSettingsButtonDialog:Button
    private lateinit var saveSettingsButtonDialog:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameout)

        setInjectActivity()

        setUpDrawer()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        var get:Boolean = false

        when (menuItem.itemId) {
            R.id.mhome -> {
                Toast.makeText(applicationContext, "HOME", Toast.LENGTH_LONG).show()
                drawerLayout.closeDrawers()
                get = true
            }
            R.id.msettings -> {
                loadDialog()
                drawerLayout.closeDrawers()
                get = true
            }
            R.id.mprofile -> {
                Toast.makeText(applicationContext, "Profiles", Toast.LENGTH_LONG).show()
                drawerLayout.closeDrawers()
                get = true
            }
        }
        return get
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.CancelSettingsButton ->{
                closeSettingDialog()
            }
            R.id.saveSettingsButton ->{
                saveSettingDialog()
            }
        }
    }

    fun addFrament(fragment: Fragment) {
        this.fragment = fragment
    }

    fun setUpDrawer() {

        toolbar = findViewById(R.id.game_toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.open_nav_toggle, R.string.close_nav_toggle
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)

        actionBarDrawerToggle.syncState()

        navigation = findViewById(R.id.navigation)
        navigation.setNavigationItemSelectedListener(this)

    }

    private fun loadDialog() {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.settings_layout)

        cancelSettingsButtonDialog = dialog.findViewById<Button>(R.id.CancelSettingsButton)
        cancelSettingsButtonDialog.setOnClickListener(this)

        saveSettingsButtonDialog = dialog.findViewById<Button>(R.id.saveSettingsButton)
        saveSettingsButtonDialog.setOnClickListener(this)

        spinner = dialog.findViewById<Spinner>(R.id.spinner)
        adapter = ArrayAdapter<Int>(
            this, android.R.layout.simple_spinner_item,
            SPKey.listOfItemToShowRecyclerView
        )
        spinner.adapter = adapter

        radioGroupLayoutManager = dialog.findViewById(R.id.radio_group_layout)
        radioButtonLinearLayout = dialog.findViewById<RadioButton>(R.id.linear_layout_radio)
        radioButtonGridLayout = dialog.findViewById<RadioButton>(R.id.grid_layout_radio)

        loadDefaultValueToSettingDialog()
    }

    private fun updateUI(){
        (fragment as GamesFragment).updateData()
        (fragment as GamesFragment).updateLayoutManagerRecyclerView()
    }

    private fun setInjectActivity() {
        (application as GameOutApp).getGameOutComponent().inject(this)
    }

    fun closeSettingDialog(){
        dialog.dismiss()
    }

    fun saveSettingDialog(){

        var spinnerItem: Int = spinner.selectedItem as Int
        var spinnerCurrenPosition: Int = spinner.selectedItemId.toInt()

        sharedPreferencesHelper.put(SPKey.SP_ELEMENT_SHOW_RECYCLER_VIEW, spinnerItem)
        sharedPreferencesHelper.put(SPKey.SP_SPINNER_POSITION, spinnerCurrenPosition)

        //Validate LayOutManager
        when (radioGroupLayoutManager.checkedRadioButtonId) {
            R.id.linear_layout_radio -> {
                sharedPreferencesHelper.put(
                    SPKey.SP_LAYOUTMANAGER_RECYCLER_VIEW,
                    SPKey.SP_LINEAR_LAYOUT_RECYCLER_VIEW
                )
            }
            R.id.grid_layout_radio -> {
                sharedPreferencesHelper.put(
                    SPKey.SP_LAYOUTMANAGER_RECYCLER_VIEW,
                    SPKey.SP_GRID_LAYOUT_RECICLER_VIEW
                )
            }
        }

        updateUI()
        closeSettingDialog()

        Toast.makeText(
            this,
            "The configurations has been saved successfully",
            Toast.LENGTH_LONG
        ).show()

    }

    fun loadDefaultValueToSettingDialog(){
        //Load Spinner Selected item
        sharedPreferencesHelper.contains(SPKey.SP_SPINNER_POSITION).let {
            if (it) {
                var positionSpinner: Int = sharedPreferencesHelper.get(SPKey.SP_SPINNER_POSITION, 0)
                spinner.setSelection(positionSpinner)
            }
        }


        //Load Radio Button seleted item
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
                                    radioButtonGridLayout.isChecked = true
                                }
                                SP_LINEAR_LAYOUT_RECYCLER_VIEW -> {
                                    radioButtonLinearLayout.isChecked = true
                                }
                            }
                        }
                    }
            }





            dialog.show()
        }
    }
}


