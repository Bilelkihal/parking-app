package com.example.Mymodule
import com.example.models.ListParckModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

// vers la fin de l'adresse donnée par ngrok faut ajouter le / pour
// formé une adresse complète par la suite en ajoutant un nom de srvice
val url = "https://reqres.in"

interface Endpoint {
    //fonction de test
    @GET("getData")
    fun getData(): Response<String>

    //retourne toute la liste
    @GET("/api/users") // mettre l'adresse de srvice
    fun getparkings(): Response<List<ListParckModel>>

    // Envoi d'un paramètre id
    @GET("getPark/{id}")
    fun detailPark(@Path("id") id:String):
            Response<ListParckModel>

    // Envoi d'un park au format JSON
   // @POST("addPark")
   // fun addPark(@Body book: ListParckModel): Response<String>
    // Envoi des attributs d'un park
   // @FormUrlEncoded

    companion object {
        @Volatile
        var endpoint: Endpoint? = null
        fun createEndpoint(): Endpoint {
            if(endpoint ==null) {
                synchronized(this) {
                    endpoint = Retrofit.Builder().baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(Endpoint::class.java)
                }
            }
            return endpoint!!

        }


    }


}