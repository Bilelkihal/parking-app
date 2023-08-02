package com.example.parkinresarvation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.parkingresarvation.R
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 ="param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"
private const val ARG_PARAM6 = "param6"
private const val ARG_PARAM7 = "param7"
private const val ARG_PARAM8 = "param8"
private const val ARG_PARAM9 = "param9"
private const val ARG_PARAM10 = "param10"
private const val ARG_PARAM11= "param11"
private const val ARG_PARAM12= "param12"
private const val ARG_PARAM13= "param13"
private const val ARG_PARAM14= "param14"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: String? = null
    private var param5: String? = null
    private var param6: String? = null
    private var param7: String? = null
    private var param8: String? = null
    private var param9: String? = null
    private var param10: String? = null
    private var param11: String? = null
    private var param12: String? = null
    private var param13: Double? = null
    private var param14: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getString(ARG_PARAM4)
            param5 = it.getString(ARG_PARAM5)
            param6 = it.getString(ARG_PARAM6)
            param7 = it.getString(ARG_PARAM7)
            param8 = it.getString(ARG_PARAM8)
            param9 = it.getString(ARG_PARAM9)
            param10 = it.getString(ARG_PARAM10)
            param11 = it.getString(ARG_PARAM11)
            param12 = it.getString(ARG_PARAM12)
            param13= it.getDouble(ARG_PARAM13)
            param14= it.getDouble(ARG_PARAM14)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_detail, container, false)
        val image : ImageView = view.findViewById(R.id.imageView3)
        val  etat: TextView = view.findViewById(R.id.textView9)
        val taux : TextView = view.findViewById(R.id.textView10)
        val  nom: TextView = view.findViewById(R.id.textView6)
        val  commune: TextView = view.findViewById(R.id.textView7)
        val  distance: TextView = view.findViewById(R.id.textView11)
        val  temps: TextView = view.findViewById(R.id.textView12)
        val  heure_ouvert: TextView = view.findViewById(R.id.textView18)
        val  heure_ferme: TextView = view.findViewById(R.id.textView19)
        val  jour: TextView = view.findViewById(R.id.textView17)
        val tarif : TextView= view.findViewById<TextView>(R.id.textView21)
        val horaire: TextView = view.findViewById<TextView>(R.id.textView20)
        val itineraire : ImageButton = view.findViewById(R.id.btnItiniraire)

        // image.setImageResource()
        param1?.let { image.setImageResource(it.toInt()) }
        etat.text= param2
        taux.text= param3
        nom.text= param4
        commune.text= param5
        distance.text=param6
        temps.text= param7
        heure_ouvert.text=param8
        heure_ferme.text= param9
        jour.text=param10
        tarif.text=param11
        horaire.text=param12
        itineraire.setOnClickListener{
            val toast = Toast.makeText(context, "Clicked", Toast.LENGTH_LONG)
            var long = param13
            var lat= param14
            toast.show()
            if (long != null && lat != null) {
                openMap(long,lat)
            }

        }
        return view
    }

    private fun openMap(long : Double, lat : Double) {
        val uri: String = java.lang.String.format(
            Locale.ENGLISH,
            "http://maps.google.com/maps?q=loc:%f,%f", long,lat
        )
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
        /* val gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988")

 // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
         val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
 // Make the Intent explicit by setting the Google Maps package
         mapIntent.setPackage("com.google.android.apps.maps")

 // Attempt to start an activity that can handle the Intent
         startActivity(mapIntent)*/
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String, param3: String, param4: String,
                        param5: String, param6: String, param7: String, param8: String,
                        param9: String, param10: String, param11: String, param12: String,
                        param13: Double, param14: Double) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, param4)
                    putString(ARG_PARAM5, param5)
                    putString(ARG_PARAM6, param6)
                    putString(ARG_PARAM7, param7)
                    putString(ARG_PARAM8, param8)
                    putString(ARG_PARAM9, param9)
                    putString(ARG_PARAM10, param10)
                    putString(ARG_PARAM11, param11)
                    putString(ARG_PARAM12, param12)
                    putDouble(ARG_PARAM13,param13)
                    putDouble(ARG_PARAM14,param14)
                }
            }
    }
}