package com.example.parkinresarvation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.adapters.ParckListAdapteur
import com.example.models.ListParckModel
import com.example.parkingresarvation.R
import com.example.parkingresarvation.register
import org.json.JSONException
import org.json.JSONObject
var theid = "0"
var valid = true;
lateinit var progressBar: ProgressBar
class Login: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        com.example.parkingresarvation.progressBar = view.findViewById(R.id.loginprogress) as ProgressBar
        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            var etEmail: EditText = view.findViewById(R.id.theemail)
            var emailUser= etEmail.text.toString()
            val etPwd: EditText = view.findViewById(R.id.pwd)
            var passwordUser= etPwd.text.toString()
            var error: TextView = view.findViewById(R.id.error);
            if(isValidEmail(emailUser)){
                if(!passwordUser.isEmpty()){
                    Login(emailUser,passwordUser)
                    com.example.parkingresarvation.progressBar.visibility = View.VISIBLE
                } else {
                    etPwd.setError("you must enter a password")
                }
            } else {
                etEmail.setError("you must enter a valid email")
            }

            if(!valid){
                error.setText("Email ou mot de passe incorrect")
            }
            Log.d("Response", "azuuul")

        }
        view.findViewById<TextView>(R.id.btnRegister).setOnClickListener {
            val fragment: Fragment = register()
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.hide(activity?.supportFragmentManager!!.findFragmentByTag("login_fragment")!!)
            transaction?.add(R.id.frame_layout, fragment, "register_fragment")
            transaction?.addToBackStack(null)
            transaction?.commit()

        }
    }

    /// login check process

        fun Login(email:String,password:String) {
        Log.d("Response", "azuuul")
        val url = "https://0129-41-220-150-38.ngrok.io/login"
        val queue = Volley.newRequestQueue(requireActivity())
        val params = HashMap<String,String>()
        params["email"] = email
        params["password"] = password
        val jsonObject = JSONObject(params as Map<*, *>)
        val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            Response.Listener { response ->
                // Process the json
                try {
                    Log.d("Response", "useridis: "+response.toString())
                    var id = response["id"];
                    Log.d("Response", "useridmriguel: "+id.toString())
                    theid = id.toString()

                    if(theid!="0"){
                        var pref = this.activity?.getSharedPreferences("db_privee", Context.MODE_PRIVATE)?.edit()
                        pref?.putBoolean("connected",true)
                        pref?.putString("id", theid)
                        pref?.apply ()
                        val fragment: Fragment = Reservation()
                        val transaction = activity?.supportFragmentManager?.beginTransaction()
                        transaction?.hide(activity?.supportFragmentManager!!.findFragmentByTag("login_fragment")!!)
                        transaction?.add(R.id.frame_layout, fragment)
                        transaction?.addToBackStack(null)
                        transaction?.commit()
                    }else{

                        valid = false;
                        com.example.parkingresarvation.progressBar.visibility = View.INVISIBLE
                        val text = "Compte inexistant"
                        val duration = Toast.LENGTH_SHORT
                        val toast = Toast.makeText(context, text, duration)
                        toast.show()
                    }
                }catch (e:Exception){
                    Log.d("bilel", e.toString())
                }

            }, Response.ErrorListener{
                // Error in request
                Log.d("kihal", it.toString())
                valid = false;
                com.example.parkingresarvation.progressBar.visibility = View.INVISIBLE
            })
        queue.add(request)
    }
    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

}