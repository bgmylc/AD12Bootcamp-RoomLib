package com.begumyolcu.urunlerroomapp.room

import androidx.room.*

@Dao
interface UrunlerDao {
    @Insert
    fun urunEkle(urun: UrunModel)

    @Update
    fun urunGuncelle(urun: UrunModel)

    @Delete
    fun urunSil(urun: UrunModel)

    @Query("SELECT * FROM urunler_tablo")
    fun tumUrunler() : List<UrunModel>

    @Query("SELECT * FROM urunler_tablo WHERE id = :key")
    fun urunGetir(key : Int) : UrunModel?
}