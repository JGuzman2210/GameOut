package org.softhk.gameout.ui.container

import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import org.softhk.gameout.R
import org.softhk.gameout.di.GameOutApp
import org.softhk.gameout.ui.game.GamesFragment
import org.softhk.gameout.utils.LayOutManagerSeleted
import org.softhk.gameout.utils.SPKey
import javax.inject.Inject

class GameActivity : AppCompatActivity(), View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var gameActivityViewModelFactory: GameActivityViewModelFactory
    lateinit var gameActivityViewModel: GameActivityViewModel

    private var fragment: Fragment? = null

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var navigation: NavigationView

    private lateinit var dialog: Dialog
    private lateinit var spinner: Spinner
    private lateinit var adapter: ArrayAdapter<Int>
    private lateinit var radioGroupLayoutManager: RadioGroup
    private lateinit var radioButtonLinearLayout: RadioButton
    private lateinit var radioButtonGridLayout: RadioButton
    private lateinit var cancelSettingsButtonDialog: Button
    private lateinit var saveSettingsButtonDialog: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gameout)

        setInjectActivity()

        setUpViewModel()

        setUpDrawer()

        setUpSettingsDialog()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        when (menuItem.itemId) {
            R.id.mhome -> {
                Toast.makeText(applicationContext, "HOME", Toast.LENGTH_LONG).show()
            }
            R.id.msettings -> {
                openSettingDialog()
            }
            R.id.mprofile -> {
                Toast.makeText(applicationContext, "Profile", Toast.LENGTH_LONG).show()
            }
        }

        drawerLayout.closeDrawers()
        return true
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.CancelSettingsButton -> {
                closeSettingDialog()
            }
            R.id.saveSettingsButton -> {
                saveSettingDialog()
                updateUI()
                closeSettingDialog()
                Toast.makeText(
                    this,
                    "The configurations has been saved successfully",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun addFrament(fragment: Fragment) {
        this.fragment = fragment
    }

    private fun setInjectActivity() {
        (application as GameOutApp).getGameOutComponent().inject(this)
    }

    private fun updateUI() {
        (fragment as GamesFragment).updateData()
        (fragment as GamesFragment).updateLayoutManagerRecyclerView()
    }

    private fun setUpViewModel() {
        gameActivityViewModel = ViewModelProviders.of(this, gameActivityViewModelFactory)
            .get(GameActivityViewModel::class.java)
    }

    private fun setUpDrawer() {

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

    private fun setUpSettingsDialog() {
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

    }

    private fun loadDefaultValueToSettingDialog() {
        //Load Spinner Selected item
        val positionSpinner = gameActivityViewModel.loadValueToSpinnerSettingInDialogView()
        spinner.setSelection(positionSpinner)

        when (gameActivityViewModel.loadSelectedLayOutManagerSettingInDialog()) {

            LayOutManagerSeleted.LINEAR_LAYOUT -> {
                radioButtonLinearLayout.isChecked = true
            }
            LayOutManagerSeleted.GRID_LAYOUT -> {
                radioButtonGridLayout.isChecked = true
            }
        }
    }

    private fun openSettingDialog() {
        loadDefaultValueToSettingDialog()
        dialog.show()
    }

    private fun closeSettingDialog() {
        dialog.dismiss()
    }

    private fun saveSettingDialog() {

        var spinnerItem: Int = spinner.selectedItem as Int
        var spinnerCurrenPosition: Int = spinner.selectedItemId.toInt()

        gameActivityViewModel.saveSettingSpinnerValue(
            spinnerItem = spinnerItem,
            spinnerPosition = spinnerCurrenPosition
        )

        //Validate LayOutManager
        when (radioGroupLayoutManager.checkedRadioButtonId) {
            R.id.linear_layout_radio ->
                gameActivityViewModel.saveSettingRecyclerLayOutManager(SPKey.SP_LINEAR_LAYOUT_RECYCLER_VIEW)

            R.id.grid_layout_radio ->
                gameActivityViewModel.saveSettingRecyclerLayOutManager(SPKey.SP_GRID_LAYOUT_RECICLER_VIEW)

        }
    }
}


