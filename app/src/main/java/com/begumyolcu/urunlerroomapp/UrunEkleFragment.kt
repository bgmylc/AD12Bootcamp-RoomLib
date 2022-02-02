package com.begumyolcu.urunlerroomapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.begumyolcu.urunlerroomapp.databinding.FragmentUrunEkleBinding


class UrunEkleFragment : Fragment() {
    private lateinit var binding: FragmentUrunEkleBinding
    //TODO: DB ekle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUrunEkleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: DB getir
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonUrunEkle.setOnClickListener {
                val urunAdInput = editTextUrunAd.text.toString()
                val urunFiyatInput = editTextUrunFiyat.text.toString().toDouble()
                val urunAdetInput = ediTextUrunAdet.text.toString().toInt()

                //TODO: DB ürün ekle

                findNavController().navigate(R.id.urunEkleToAnasayfa)
            }


        }
    }
}