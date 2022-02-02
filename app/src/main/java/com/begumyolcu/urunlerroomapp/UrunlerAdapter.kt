package com.begumyolcu.urunlerroomapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.begumyolcu.urunlerroomapp.databinding.ItemCardBinding
import com.begumyolcu.urunlerroomapp.room.UrunModel

class UrunlerAdapter(private var urunlerList: List<UrunModel?>) :
    RecyclerView.Adapter<UrunlerAdapter.CardHolder>() {

    class CardHolder(val itemCardBinding: ItemCardBinding) : RecyclerView.ViewHolder(itemCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val itemCardBinding = ItemCardBinding.inflate(layoutInflater, parent, false)
        return CardHolder(itemCardBinding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val urun = urunlerList[position]

        holder.itemCardBinding.apply {
            urun?.let{
                textViewUrunAd.text = urun.urunAd
                textViewUrunFiyat.text = urun.urunFiyat.toString()
                textViewUrunAdet.text = urun.urunAdet.toString()
            }

            itemCard.setOnClickListener {button ->
                urun?.let {
                    val anasayfaToDetay = AnasayfaFragmentDirections.anasayfaToGuncelle(urun)
                    Navigation.findNavController(button).navigate(anasayfaToDetay)
                }
            }
        }
    }

    override fun getItemCount() = urunlerList.size


}