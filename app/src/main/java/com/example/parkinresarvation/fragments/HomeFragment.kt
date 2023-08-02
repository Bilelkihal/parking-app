package com.example .parkinresarvation.fragments

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.Mymodule.Endpoint
import com.example.adapters.ParckListAdapteur
import com.example.models.ListParckModel
import com.example.objects.RetrofitService
import com.example.parkingresarvation.R
import com.example.parkinresarvation.fragments.DetailFragment
import com.example.viewmodel.ParkingsModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path




import android.widget.Toast.makeText as makeText1


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), ParckListAdapteur.ClickListner {
    private lateinit var adapter: ParckListAdapteur
    var listData: ArrayList<ListParckModel> = ArrayList()
    var listSearch: ArrayList<ListParckModel> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var imageId: Array<Int>
    lateinit var id: Array<Int>
    lateinit var nom: Array<String>
    lateinit var etat: Array<String>
    lateinit var taux: Array<String>
    lateinit var distance: Array<String>
    lateinit var temps: Array<String>
    lateinit var commune: Array<String>
    lateinit var tarif: Array<String>
    lateinit var horaire: Array<String>
    lateinit var heure_ouvert: Array<String>
    lateinit var heure_ferme: Array<String>
    lateinit var jour: Array<String>
    lateinit var progressBar: ProgressBar
    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    lateinit var searchbar : EditText
    lateinit var maxdis : EditText
    lateinit var maxp : EditText

    var userlong : Double = 3.1523987
    var userlat : Double = 36.7159496

    var searchlong : Double = 0.0
    var searchlat : Double = 0.0

    var maxdistance : Double = 5.0
    var maxPrice : Double = 15.0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //buildDisplayData()
        searchbar = view.findViewById(R.id.searchbar)
        maxdis = view.findViewById(R.id.distance)
        maxp = view.findViewById(R.id.price)

        view.findViewById<ImageView>(R.id.searchbutton).setOnClickListener{
            if (!maxdis.text.toString().isEmpty()){
                maxdistance = maxdis.text.toString().toDouble()
            }

            if (!maxp.text.toString().isEmpty()){
                maxPrice = maxp.text.toString().toDouble()
            }

            var geocoder = Geocoder(requireActivity())
            listSearch.clear()
            try {
                progressBar.visibility = View.VISIBLE
                //listData.clear()
                //recyclerView?.adapter?.notifyDataSetChanged()
                var geoResults = geocoder.getFromLocationName(searchbar.text.toString(), 1)
                /*while (geoResults.size == 0) {
                    geoResults = geocoder.getFromLocationName(searchbar.text.toString(), 1)
                }

                 */
                if (geoResults.size > 0) {
                    val addr = geoResults[0]
                    searchlong = addr.longitude
                    searchlat = addr.latitude
                    Log.d("------------------","---------------------" +
                            "--------------------------------" +
                            "------------------------------")

                }
            } catch (e: Exception) {
                print(e.message)
            }
            //search()
            var i = 0
            for(park in listData){
                i++
                var park0 =park.longitude
                var park00 = park.latitude
                var v1 = LatLng(park0,park00)
                var v2 = LatLng(searchlat, searchlong)
                var leprix = park.tarif.toDouble()
                var thedistance = SphericalUtil.computeDistanceBetween(v1, v2)
                // convert miles to Km
                thedistance = thedistance*1.60934/1000;
                // round the number
                thedistance = Math.round(thedistance * 10.0) / 10.0
                if(thedistance<maxdistance){
                    if(leprix<maxPrice){
                        listSearch.add(park)
                    }
                }
            }



            Log.d("------------------","---------------------" +
                    "--------------------------------" +
                    "------------------------------")
            Log.d("------------------",i.toString())

            Log.d("------------------","---------------------" +
                    "--------------------------------" +
                    "------------------------------")
            //recyclerView?.adapter?.notifyDataSetChanged()

            adapter = ParckListAdapteur(listSearch, this)
            recyclerView.adapter = adapter
            recyclerView?.adapter?.notifyDataSetChanged()
            progressBar.visibility = View.INVISIBLE


        }

        initRecyclerView(view)
        return view
    }
    private fun initRecyclerView(view: View) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),101)
            return
        }
        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener {
            if(it!=null){
                Log.d("longitude", it.longitude.toString())
                userlong = it.longitude
                Log.d("latitude", it.latitude.toString())
                userlat = it.latitude
            }
        }

        //


        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        getParingsFragment()
        //adapter = ParckListAdapteur(listData, this)
        //recyclerView.adapter = adapter



    }
    private fun getParingsFragment (){

        //val url = "https://reqres.in/api/users"
        val url = "https://7066-41-220-150-38.ngrok.io/getparkings"
        val queue = Volley.newRequestQueue(requireActivity())


        val request = JsonArrayRequest(
            Request.Method.GET, // method
            url, // url
            null, // json request
            {response -> // response listener
                try {
                    Log.d("Response", "1111111111111111111111111")
                    Log.d("Response", response.toString())
                    // loop through the array elements
                    for (i in 0 until response.length()) {
                        Log.d("Response", "22222222222222222222222222")
                        val theparking = response.getJSONObject(i)
                        var Name = theparking.get("parkingname")
                        var adresse = theparking.get("adress")
                        var id = theparking.get("idparking")
                        var state = theparking.get("currentstate")
                        var thestate = ""
                        if(state.toString() == "false"){
                            thestate = "fermé"
                        } else{
                            thestate = "ouvert"
                        }
                        var taux = theparking.get("rateOccupation")
                        var distance = theparking.get("distance")
                        var time = theparking.get("time")
                        var openningHour = theparking.get("openningHour")
                        var closingHour = theparking.get("closingHour")
                        var day = theparking.get("day")
                        var cost = theparking.get("cost")
                        var horaire = theparking.get("horaire")
                        var long = theparking.get("longitude")
                        var parkinglongitude  = long.toString()
                        var latitude = theparking.get("latitude")
                        var parkinglatitude  = latitude.toString()
                        // calcule de la distance entre le client et le parking
                        val posistionParking = LatLng(parkinglatitude.toDouble(),parkinglongitude.toDouble())
                        val posClinet = LatLng(userlong, userlat )

                        var thedistance = SphericalUtil.computeDistanceBetween(posistionParking, posClinet)
                        // convert miles to Km
                        thedistance = thedistance*1.60934/1000;
                        // round the number
                        thedistance = Math.round(thedistance * 10.0) / 10.0


                        progressBar.visibility = View.INVISIBLE


                        ///////// Bismillah
                        val parking = ListParckModel(
                            1, R.drawable.parc1, thestate, taux.toString(), Name.toString(),
                            adresse.toString(), thedistance.toString()+" Km  ", time.toString(),openningHour.toString(),closingHour.toString(),day.toString(),cost.toString(),
                            horaire.toString(),
                            long as Double,
                            latitude as Double
                        )
                        listData.add(parking)
                        Log.d("Response", "parking created")



                    }
                    Log.d("Listdata out", "parking created")
                    adapter = ParckListAdapteur(listData, this)
                    recyclerView.adapter = adapter



                }catch (e: JSONException){
                    Log.d("Response", "3333333333333333333333333333")
                    progressBar.visibility = View.INVISIBLE
                }

            },
            {error -> // error listener
                progressBar.visibility = View.INVISIBLE
                Log.d("Response", "44444444444444444444444444444")
                Log.d("Response", error.message.toString())

            }
        )




        queue.add(request)

        //////////////// End Douaa code



    }


    private fun search(){

        val url = "https://7066-41-220-150-38.ngrok.io/getparkings"
        val queue = Volley.newRequestQueue(requireActivity())


        val request = JsonArrayRequest(
            Request.Method.GET, // method
            url, // url
            null, // json request
            {response -> // response listener
                try {
                    Log.d("Response", "1111111111111111111111111")
                    Log.d("Response", response.toString())
                    // loop through the array elements
                    for (i in 0 until response.length()) {
                        Log.d("Response", "22222222222222222222222222")
                        val theparking = response.getJSONObject(i)
                        var Name = theparking.get("parkingname")
                        var adresse = theparking.get("adress")
                        var id = theparking.get("idparking")
                        var state = theparking.get("currentstate")
                        var thestate = ""
                        if(state.toString() == "false"){
                            thestate = "fermé"
                        } else{
                            thestate = "ouvert"
                        }
                        var taux = theparking.get("rateOccupation")
                        var distance = theparking.get("distance")
                        var time = theparking.get("time")
                        var openningHour = theparking.get("openningHour")
                        var closingHour = theparking.get("closingHour")
                        var day = theparking.get("day")
                        var cost = theparking.get("cost")
                        var horaire = theparking.get("horaire")
                        var long = theparking.get("longitude")
                        var parkinglongitude  = long.toString()
                        var latitude = theparking.get("latitude")
                        var parkinglatitude  = latitude.toString()
                        // calcule de la distance entre le client et le parking
                        val posistionParking = LatLng(parkinglatitude.toDouble(),parkinglongitude.toDouble())
                        val posClinet = LatLng(userlong, userlat )

                        val posSearch = LatLng(searchlong, searchlat)

                        var thedistance = SphericalUtil.computeDistanceBetween(posistionParking, posClinet)
                        // convert miles to Km
                        thedistance = thedistance*1.60934/1000;
                        // round the number
                        thedistance = Math.round(thedistance * 10.0) / 10.0


                        var distancesearch = SphericalUtil.computeDistanceBetween(posistionParking, posSearch)
                        // convert miles to Km
                        distancesearch = thedistance*1.60934/1000;
                        // round the number
                        distancesearch = Math.round(thedistance * 10.0) / 10.0
                        Log.d("distancesearch: ", distancesearch.toString())



                        progressBar.visibility = View.INVISIBLE


                        ///////// Bismillah
                        val parking = ListParckModel(
                            1, R.drawable.parc1, thestate, taux.toString(), Name.toString(),
                            adresse.toString(), thedistance.toString()+" Km  ", time.toString(),openningHour.toString(),closingHour.toString(),day.toString(),cost.toString()+" DZD",
                            horaire.toString(),
                            long as Double,
                            latitude as Double
                        )
                        if(distancesearch<5){

                            listData.add(parking)
                        }
                        Log.d("Response", "parking created")



                    }
                    Log.d("Listdata out", "parking created")
                    //adapter = ParckListAdapteur(listData, this)
                    //recyclerView.adapter = adapter



                }catch (e: JSONException){
                    Log.d("Response", "3333333333333333333333333333")
                    progressBar.visibility = View.INVISIBLE
                }

            },
            {error -> // error listener
                progressBar.visibility = View.INVISIBLE
                Log.d("Response", "44444444444444444444444444444")
                Log.d("Response", error.message.toString())

            }
        )




        queue.add(request)

        //////////////// End Douaa code



    }



    companion object {
        @JvmStatic
        fun newInstanceList() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }

    }
    override fun onItemClick(listData: ListParckModel) {
        val fragment: Fragment = DetailFragment.newInstance(listData.titleImage,
            listData.etat,listData.taux,listData.nom,listData.commune, listData.distance,listData.temps,
            listData.heure_ouvert,listData.heure_ferme, listData.jour,listData.tarif,listData.horaire,listData.longitude,listData.latitude)
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.hide(activity?.supportFragmentManager!!.findFragmentByTag("list_fragment")!!)
        transaction?.add(R.id.frame_layout, fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

}