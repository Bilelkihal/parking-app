package com.example.dao

import androidx.room.*
import com.example.entities.Reservation

@Dao
interface ReservationDao {

    @Query("select * from Reservation ")
    fun getAllReservations(): List<Reservation>

    @Query("SELECT * from Reservation where code_Qr=:code_Qr")
    fun getReservationByCodeQR(code_Qr:String):Reservation
    @Query("SELECT * from Reservation where heure_sortie >:heure_actuelle")
    fun getCurrentReservation(heure_actuelle: Long):List<Reservation>

    @Insert
    fun addReservation(vararg reserv: Reservation)
    @Update
    fun updateReservation(reserv: Reservation)
    @Delete
    fun deleteReservation(reserv: Reservation)
    abstract fun insert(reservation: Reservation)
}