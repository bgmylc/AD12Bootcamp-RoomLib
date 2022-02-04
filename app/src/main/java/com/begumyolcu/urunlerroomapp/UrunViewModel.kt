package com.begumyolcu.urunlerroomapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.begumyolcu.urunlerroomapp.room.UrunModel
import com.begumyolcu.urunlerroomapp.room.UrunlerDao
import kotlinx.coroutines.launch

class UrunViewModel(val db: UrunlerDao, application: Application) : AndroidViewModel(application) {
    var urunlerList = MutableLiveData<List<UrunModel>>()

    init {
        getTumUrunler()
    }


    private fun getTumUrunler() {
        viewModelScope.launch {
            urunlerList.value = db.tumUrunler()
        }
    }

    fun ekleUrun(urun : UrunModel){
        viewModelScope.launch {
            db.urunEkle(urun)
        }
    }

    fun guncelleUrun(urun: UrunModel) {
        viewModelScope.launch {
            db.urunGuncelle(urun)
        }
    }

    fun silUrun(urun: UrunModel) {
        viewModelScope.launch {
            db.urunSil(urun)
        }
    }
}