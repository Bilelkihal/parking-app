package com.example.parkingresarvation

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.bdd.roomDatabase
import com.example.entities.Reservation
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import java.util.*

class TestReservation {
    lateinit var dataBase: roomDatabase

    @Before
    fun initDB() {
        dataBase =
            Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context,
                roomDatabase::class.java
            ).build()
    }

    @Test
    fun testGetAllReservations() {
        dataBase.getReservationDao().insert(
            Reservation(
                dateReservation = Date(),
                heure_entree = System.currentTimeMillis(),
                heure_sortie = System.currentTimeMillis(),
                etatReservation = "En cours",
                code_Qr = "125",
                numero_place = 15
            )
        )
        assertEquals(dataBase.getReservationDao().getAllReservations().size, 1)
    }

    @Test
    fun testGetCurrentReservations() {
        dataBase.getReservationDao().insert(
            Reservation(
                dateReservation = Date(),
                heure_entree = System.currentTimeMillis(),
                heure_sortie = System.currentTimeMillis(),
                etatReservation = "En cours",
                code_Qr = "125",
                numero_place = 15
            )
        )
        assertEquals(dataBase.getReservationDao().getCurrentReservation(System.currentTimeMillis()).size, 0)
        dataBase.getReservationDao().insert(
            Reservation(
                dateReservation = Date(),
                heure_entree = System.currentTimeMillis(),
                heure_sortie = System.currentTimeMillis()+100000000000000,
                etatReservation = "En cours",
                code_Qr = "1000000",
                numero_place = 15
            )
        )
        assertEquals(dataBase.getReservationDao().getCurrentReservation(System.currentTimeMillis()).size, 1)
    }


    @Test
    fun testAddReservation() {
        val reservation: Reservation = Reservation(
            dateReservation = Date(),
            heure_entree = System.currentTimeMillis(),
            heure_sortie = System.currentTimeMillis(),
            etatReservation = "En cours",
            code_Qr = "456123123123",
            numero_place = 10
        )
        dataBase.getReservationDao().insert(reservation)
        val reservationTest = dataBase.getReservationDao().getReservationByCodeQR("456123123123")
        assertEquals(reservation, reservationTest)
    }

    @After
    fun closeDb(){
        dataBase?.close()
    }

}