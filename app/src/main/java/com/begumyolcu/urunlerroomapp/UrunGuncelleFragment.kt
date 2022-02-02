package com.begumyolcu.urunlerroomapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.begumyolcu.urunlerroomapp.databinding.FragmentUrunGuncelleBinding

class UrunGuncelleFragment : Fragment() {
    private lateinit var binding: FragmentUrunGuncelleBinding
//    private lateinit var urun: UrunModel
    //TODO: DB ekle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUrunGuncelleBinding.inflate(inflater, container, false)

//        val bundle: UrunGuncelleFragmentArgs by navArgs()
//        urun = bundle.urun
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: DB getir
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val urunID = urun.id
        binding.apply {
//            editTextGuncelleUrunAd.text = urun.urunAd
//            editTextGuncelleUrunFiyat.text = urun.urunFiyat.toString()
//            editTexGuncelleUrunAdet.text = urun.urunAdet.toString()

            buttonUrunGuncelle.setOnClickListener {
                val adInput = editTextGuncelleUrunAd.text.toString()
                val fiyatInput = editTextGuncelleUrunFiyat.text.toString().toDouble()
                val adetInput = editTexGuncelleUrunAdet.text.toString().toInt()

                //TODO: DB ürün guncelle

                findNavController().navigate(R.id.guncelleToAnasayfa)
            }

            buttonUrunSil.setOnClickListener {
                //TODO: DB urun sil

                findNavController().navigate(R.id.guncelleToAnasayfa)
            }
        }
    }

}