package com.begumyolcu.urunlerroomapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "urunler_tablo")
data class UrunModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "urun_ad")
    var urunAd: String,
    @ColumnInfo(name= "urun_fiyat")
    var urunFiyat: Double,
    @ColumnInfo(name= "urun_adet")
    var urunAdet: Int
) : Serializable
