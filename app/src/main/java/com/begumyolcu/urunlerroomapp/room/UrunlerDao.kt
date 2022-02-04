package com.begumyolcu.urunlerroomapp.room

import androidx.room.*

@Dao
interface UrunlerDao {
    @Insert
    suspend fun urunEkle(urun: UrunModel)

    @Update
    suspend fun urunGuncelle(urun: UrunModel)

    @Delete
    suspend fun urunSil(urun: UrunModel)

    @Query("SELECT * FROM urunler_tablo")
    suspend fun tumUrunler(): List<UrunModel>

    @Query("SELECT * FROM urunler_tablo WHERE id = :key")
    suspend fun urunGetir(key: Int): UrunModel?
}