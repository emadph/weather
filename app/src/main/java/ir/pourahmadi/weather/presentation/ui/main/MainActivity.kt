package ir.pourahmadi.weather.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ir.pourahmadi.weather.R
import ir.pourahmadi.weather.presentation.common.gone
import ir.pourahmadi.weather.presentation.common.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setupBottomNavigation()

    }

    private fun init() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController
        nav_view.setupWithNavController(navController)
    }

    private fun setupBottomNavigation() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.HomeParentFragment -> nav_view.visible()
                R.id.CategoryFragment -> nav_view.visible()
                R.id.PeopleTalkParentFragment -> nav_view.visible()
                else -> nav_view.gone()
            }
        }
    }

}
