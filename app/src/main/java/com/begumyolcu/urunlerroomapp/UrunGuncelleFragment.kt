package com.begumyolcu.urunlerroomapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.begumyolcu.urunlerroomapp.databinding.FragmentUrunGuncelleBinding
import com.begumyolcu.urunlerroomapp.room.UrunModel
import com.begumyolcu.urunlerroomapp.room.UrunlerDatabase

class UrunGuncelleFragment : Fragment() {
    private lateinit var binding: FragmentUrunGuncelleBinding
    private lateinit var urun: UrunModel
    private lateinit var urunDB: UrunlerDatabase
    private lateinit var urunViewModel: UrunViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_urun_guncelle, container, false)

        val bundle: UrunGuncelleFragmentArgs by navArgs()
        urun = bundle.urun

        val application = requireNotNull(this.activity).application
        val dataSource = UrunlerDatabase.getUrunlerDatabase(application)?.urunlerDao

        val viewModelFactory = dataSource?.let{ UrunViewModelFactory(it, application)}
        urunViewModel = viewModelFactory?.let {
            ViewModelProvider(this, it).get(UrunViewModel::class.java)
        }!!

        binding.setLifecycleOwner(this)

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

                urunViewModel.guncelleUrun(urun)

                findNavController().navigate(R.id.guncelleToAnasayfa)
            }

            buttonUrunSil.setOnClickListener {
                urunViewModel.silUrun(urun)

                findNavController().navigate(R.id.guncelleToAnasayfa)
            }
        }
    }

}