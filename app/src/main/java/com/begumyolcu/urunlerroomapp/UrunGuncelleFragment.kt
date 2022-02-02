package com.begumyolcu.urunlerroomapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.begumyolcu.urunlerroomapp.databinding.FragmentUrunGuncelleBinding
import com.begumyolcu.urunlerroomapp.room.UrunModel
import com.begumyolcu.urunlerroomapp.room.UrunlerDatabase

class UrunGuncelleFragment : Fragment() {
    private lateinit var binding: FragmentUrunGuncelleBinding
    private lateinit var urun: UrunModel
    private lateinit var urunDB: UrunlerDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUrunGuncelleBinding.inflate(inflater, container, false)

        val bundle: UrunGuncelleFragmentArgs by navArgs()
        urun = bundle.urun

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        urunDB = UrunlerDatabase.getUrunlerDatabase(requireContext())!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val urunID = urun.id
        binding.apply {
            editTextGuncelleUrunAd.setText(urun.urunAd)
            editTextGuncelleUrunFiyat.setText(urun.urunFiyat.toString())
            editTexGuncelleUrunAdet.setText(urun.urunAdet.toString())

            buttonUrunGuncelle.setOnClickListener {
                val adInput = editTextGuncelleUrunAd.text.toString()
                val fiyatInput = editTextGuncelleUrunFiyat.text.toString().toDouble()
                val adetInput = editTexGuncelleUrunAdet.text.toString().toInt()

                urun.urunAd = adInput
                urun.urunFiyat = fiyatInput
                urun.urunAdet = adetInput

                urunDB.urunlerDao.urunGuncelle(urun)

                findNavController().navigate(R.id.guncelleToAnasayfa)
            }

            buttonUrunSil.setOnClickListener {
                urunDB.urunlerDao.urunSil(urun)

                findNavController().navigate(R.id.guncelleToAnasayfa)
            }
        }
    }

}