package com.example.parkingresarvation

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.parkinresarvation.fragments.Login
import com.example.parkinresarvation.fragments.Reservation
import com.example.parkinresarvation.fragments.theid
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var progressBar: ProgressBar
/**
 * A simple [Fragment] subclass.
 * Use the [register.newInstance] factory method to
 * create an instance of this fragment.
 */
class register : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.registerprogress) as ProgressBar
        view.findViewById<ImageView>(R.id.arrowback).setOnClickListener {
            Log.d("Response", "arrow back clicked!")
            val fragment: Fragment = Login()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.hide(activity?.supportFragmentManager!!.findFragmentByTag("register_fragment")!!)
            transaction?.add(R.id.frame_layout, fragment, "login_fragment")
            transaction?.addToBackStack(null)
            transaction?.commit()
        }


        view.findViewById<Button>(R.id.register).setOnClickListener {
            val nom: EditText = view.findViewById(R.id.nom)
            var thenom= nom.text.toString()
            val prenom: EditText = view.findViewById(R.id.prenom)
            var theprenom= prenom.text.toString()
            val telephone: EditText = view.findViewById(R.id.telephone)
            var thetelephone= telephone.text.toString()
            val email: EditText = view.findViewById(R.id.email)
            var theemail= email.text.toString()
            val password: EditText = view.findViewById(R.id.password)
            var thepassword= password.text.toString()
            val confirmepassword: EditText = view.findViewById(R.id.confirmepassword)
            var theconfirmepassword= confirmepassword.text.toString()

            // text fields validation
            if(!thenom.isEmpty()){
                if(!theprenom.isEmpty()){
                if(isValidEmail(theemail)){
                    if(!thetelephone.isEmpty()){
                        if(!thepassword.isEmpty()){
                                if(!(thepassword!=theconfirmepassword)){
                                    Register(thenom,theprenom,thetelephone,theemail,thepassword)
                                    progressBar.visibility = View.VISIBLE
                                } else {
                                    confirmepassword.setError("Passwords does not match")
                                }
                            } else {
                                password.setError("This field is required")
                            }
                        }else{
                            telephone.setError("This field is required")
                        }
                    } else{
                        email.setError("You must enter a valid email")
                    }
                } else{
                    prenom.setError("This field is required")
                }
            } else {
                nom.setError("This field is required")
            }







            Log.d("Response", "azuuul")

        }


        // code
    }

    fun Register(nom:String,prenom:String,telephone:String,email:String,password:String) {
        Log.d("Response", "azuuul")
        val url = "https://7066-41-220-150-38.ngrok.io/register"
        val queue = Volley.newRequestQueue(requireActivity())
        val params = HashMap<String,String>()
        params["nom"] = nom
        params["prenom"] = prenom
        params["telephone"] = telephone
        params["email"] = email
        params["password"] = password
        val jsonObject = JSONObject(params as Map<*, *>)
        val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            Response.Listener { response ->
                // Process the json

                    Log.d("Response", "Response is: "+response.toString())
                    val fragment: Fragment = Login()
                    val transaction = activity?.supportFragmentManager?.beginTransaction()
                    transaction?.hide(activity?.supportFragmentManager!!.findFragmentByTag("register_fragment")!!)
                    transaction?.add(R.id.frame_layout, fragment, "login_fragment")
                    transaction?.addToBackStack(null)
                    transaction?.commit()
                    Log.d("Response", "commited")


            }, Response.ErrorListener{
                // Error in request
                //Log.d("Response", it.toString())
                Log.d("Response", "exception")
                val fragment: Fragment = Login()
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.hide(activity?.supportFragmentManager!!.findFragmentByTag("register_fragment")!!)
                transaction?.add(R.id.frame_layout, fragment, "login_fragment")
                transaction?.addToBackStack(null)
                transaction?.commit()
            })
        queue.add(request)
    }
    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

}