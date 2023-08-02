package com.example.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class Reservation(
    var dateReservation: Date,
    var heure_entree: Long,
    var heure_sortie: Long,
    var code_Qr:String,
    var numero_place:Int,
    var etatReservation: String
){
    @PrimaryKey(autoGenerate = true)
    var num_reservation:Int = 0
}
