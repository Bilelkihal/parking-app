package com.example.parkingresarvation.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.parkingresarvation.R
import com.example.parkinresarvation.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainActivity : AppCompatActivity() {
    lateinit var currentFragment : Fragment
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, currentFragment, "list_fragment")
            .commit()
        val bottomNavigation: BottomNavigationView = findViewById(R.id.menu_bottom)
        bottomNavigation.setOnItemSelectedListener(naviListner)



    }

    val naviListner= BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val pref = getSharedPreferences("db_privee",Context.MODE_PRIVATE)
        val isConnected = pref.getBoolean("connected", false)
        val currentEmail=pref.getString("email","")
        val cuurentPassword=pref.getString("password","")
        when (item.itemId) {
            R.id.home_icon -> {
                currentFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, currentFragment, "list_fragment")
                    .commit()

            }
            R.id.map_icon -> {

                val intent = Intent (currentFragment.context, MapActivity::class.java)
                startActivity(intent)

            }

            R.id.reserv_icon -> {
                if(!isConnected) {

                    currentFragment = Login()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, currentFragment,"login_fragment")
                        .commit()
                }
                    else{
                    currentFragment = Reservation()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, currentFragment,"reservation_fragment")
                        .commit()
                    }


            }

            R.id.profil_icon -> {

                currentFragment = ProfilFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, currentFragment)
                    .commit()


            }
        }

        true

    }

}










