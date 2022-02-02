package com.begumyolcu.urunlerroomapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UrunModel::class], version = 1, exportSchema = false)
abstract class UrunlerDatabase : RoomDatabase() {

    abstract val urunlerDao : UrunlerDao

    companion object {

        @Volatile
        private var INSTANCE: UrunlerDatabase? = null

        fun getUrunlerDatabase(context: Context) : UrunlerDatabase? {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UrunlerDatabase::class.java,
                        "urunler_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return INSTANCE
            }
        }
    }
}