package com.example.parkinresarvation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.parkingresarvation.R

class Reservation: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservation, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var pref = this.activity?.getSharedPreferences("db_privee", Context.MODE_PRIVATE)?.edit()
        view.findViewById<Button>(R.id.btnLogout).setOnClickListener{
            pref?.putBoolean("connected",false)
            pref?.putString("email","")
            pref?.putString("password","")
            pref?.apply ()
            /*this.activity?.findNavController(R.id.navHost)
                ?.navigate(R.id.homeFragment)*/
            val fragment: Fragment = HomeFragment()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.hide(activity?.supportFragmentManager!!.findFragmentByTag("reservation_fragment")!!)
            transaction?.add(R.id.frame_layout, fragment)
            transaction?.addToBackStack(null)
            transaction?.commit()
        }
    }
}