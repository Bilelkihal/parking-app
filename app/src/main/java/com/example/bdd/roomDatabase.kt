package com.example.bdd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.converters.DataConverter
import com.example.dao.ReservationDao
import com.example.entities.Reservation

@Database(entities = [Reservation::class],version = 1)
@TypeConverters(DataConverter::class)
abstract class roomDatabase: RoomDatabase()  {

        abstract fun getReservationDao():ReservationDao
        companion object{
            @Volatile
            private var INSTANCE: roomDatabase? = null
            fun buildDatabase(context: Context): roomDatabase? {
                if (INSTANCE == null) { synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context,roomDatabase::class.java,
                        "db_name")
                    .allowMainThreadQueries().build() } }
                return INSTANCE }
        }

}