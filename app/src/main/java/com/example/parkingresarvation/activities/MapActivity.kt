package com.example.parkingresarvation.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.parkingresarvation.R
import com.example.parkingresarvation.databinding.ActivityMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONException

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private lateinit var binding: ActivityMapBinding
    private var list: ArrayList<LatLng>? = null
    private var pos1:LatLng = LatLng(36.75041 ,3.07794)
    private var pos2:LatLng = LatLng(36.73494 ,3.03209)
    private var pos3:LatLng = LatLng(36.75685 ,3.0305)
    private var pos4:LatLng = LatLng(36.75041 ,3.07794)
    private var pos5:LatLng = LatLng(36.75685 ,3.0305)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
        Log.d("TestMapFragrment", "*********************")
        list= ArrayList()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val url = "https://2434-41-220-150-38.ngrok.io/getparkings"
        val queue = Volley.newRequestQueue(this)
        Log.d("create2", " on Create2************************")

        val request = JsonArrayRequest(
            Request.Method.GET, // method
            url, // url
            null,
            {response -> // response listener
                Log.d("create3", " on Create3************************")
                try {
                    Log.d("create1", " on Create ************************")
                    // loop through the array elements
                    for (i in 0 until response.length()) {
                        val theparking = response.getJSONObject(i)
                        var long = theparking.get("longitude").toString()
                        var lati = theparking.get("latitude").toString()
                        var position : LatLng = LatLng(long.toDouble(), lati.toDouble())
                        Log.d("long ", long+"************************")
                        Log.d("lat ", lati+"************************")
                        Log.d("position ", position.toString()+"************************")
                        Log.d("element", " Add element************************")
                        list!!.add(position)
                        /*
                           Log.d("longitude1", list!![0].latitude.toString()+"*********************")
                            Log.d("longitude2", list!![1].latitude.toString()+"*********************")
                         Log.d("longitude3", list!![2].latitude.toString()+"*********************")*/


                    }




                }catch (e: JSONException){
                    Log.d("Exception", "3333333333333333333333333333")
                }

            },
            {error -> // error listener
                Toast.makeText(this,error.message, Toast.LENGTH_SHORT).show()
                Log.d("Error", "Erreur get response")
            })
        queue.add(request)
        list!!.add(pos1)
        list!!.add(pos2)
        list!!.add(pos3)
        list!!.add(pos4)
        list!!.add(pos5)


        // on below line we are adding our
        // locations in our array list.

        // on below line we are adding our
        // locations in our array list.
//        locationArrayList!!.addAll(getPosition()!!.filterNotNull())
        //      Log.d("longitude11", locationArrayList!![0].latitude.toString()+"*********************")
        //  Log.d("longitude21", locationArrayList!![1].latitude.toString()+"*********************")
        //    Log.d("longitude31", locationArrayList!![2].latitude.toString()+"*********************")


    }

/*private fun setMap(googleMap: GoogleMap){
    mMap = googleMap
    Log.d("onMapReady", "*********************")
    for (i in list!!.indices) {
        Log.d("longitude", list!![i].latitude.toString()+"*********************")
        val latlong = LatLng(list!![i].latitude,list!![i].longitude )

        // below line is use to add marker to each location of our array list.
        mMap!!.addMarker(MarkerOptions().position(latlong).title("Markertest"))

        // below lin is use to zoom our camera on map.
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(18.0f))

        // below line is use to move our camera to the specific location.
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(list!![i]))
    }

}*/
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        Log.d("onMapReady", "*********************")
        val pos = LatLng(36.75685 ,3.0305)
        for (i in list!!.indices) {
            Log.d("longitude", list!![i].latitude.toString()+"*********************")
            val latlong = LatLng(list!![i].latitude,list!![i].longitude )

            // below line is use to add marker to each location of our array list.
            mMap!!.addMarker(MarkerOptions().position(latlong).title("Markertest"))

            // below lin is use to zoom our camera on map.
            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 12.0f), 5000, null);

            // below line is use to move our camera to the specific location.
            mMap!!.moveCamera(CameraUpdateFactory.newLatLng(list!![i]))
        }

    }
}