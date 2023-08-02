package com.example.models

import com.google.gson.annotations.SerializedName

data class ListParckModel
   (
   @SerializedName("idParking")
   var id :Int,
   var titleImage :Int ,
   @SerializedName("currentstate")
   var etat: String,
   @SerializedName("rateOccupation")
   var taux :String,
   @SerializedName("parkingname")
   var nom :String,
   @SerializedName("adress")
   var commune : String ,
   @SerializedName("distance")
   var distance :String,
   @SerializedName("time")
   var temps :String,
   @SerializedName("openningHour")
   var heure_ouvert: String,
   @SerializedName("closingHour")
   var heure_ferme : String,
   @SerializedName("day")
   var jour : String ,
   @SerializedName("cost")
   var tarif : String,
   @SerializedName("horaire")
   var horaire : String,
   var longitude: Double,
   var latitude: Double,)

{
}