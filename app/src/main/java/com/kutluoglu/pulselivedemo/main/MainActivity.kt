package com.kutluoglu.pulselivedemo.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.kutluoglu.pulselivedemo.R
import com.kutluoglu.pulselivedemo.base.BaseActivity
import com.kutluoglu.pulselivedemo.utils.extensions.setPLTitle
import com.kutluoglu.pulselivedemo.utils.extensions.setupActionbar
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        setupNavigationEnvironment()
    }


    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onNavigateUp() = navController.navigateUp()

    override fun onBackPressed() {
        super.onBackPressed()
        toolbar.setPLTitle(getString(R.string.pulse_live_content))
    }

    private fun setupNavigationEnvironment() {
        setupActionbar(toolbar)
        setupNavigation()
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.nav_host_fragment)

        // Tie actionbar/toolbar items to navController
        appBarConfig = AppBarConfiguration(navController.graph)

        // Top Level Destinations
        appBarConfig.topLevelDestinations.add(R.id.contents)

        // Tie all together
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfig)
    }
}
