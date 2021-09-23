package com.sol.soccerleague

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.sol.soccerleague.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.instance.appComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment_activity_main)

        setupActionBarWithNavController(navController!!)

        supportActionBar?.hide()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp() || super.onSupportNavigateUp()
    }
}