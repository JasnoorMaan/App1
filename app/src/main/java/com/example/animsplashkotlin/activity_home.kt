package com.example.animsplashkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fragments.BlogFragment
import fragments.HomeFragment
import fragments.ProfileDevicesFragment

class activity_home : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val dashboardFragment = BlogFragment()
    private val notificationsFragment = ProfileDevicesFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        replaceFragment(homeFragment)


        findViewById<>(R.id.bottom_navigation)
            .setOnNavigationItemSelectedListener {
            when (it) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.navigation_dashboard -> replaceFragment(dashboardFragment)
                R.id.navigation_notifications -> replaceFragment(notificationsFragment)

            }
            true
        }
    }



        fun replaceFragment(fragment: Fragment) {
            if (fragment != null) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, fragment)
                transaction.commit()
            }

        }
    }
