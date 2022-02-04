package com.begumyolcu.urunlerroomapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.begumyolcu.urunlerroomapp.databinding.FragmentUrunEkleBinding
import com.begumyolcu.urunlerroomapp.room.UrunModel
import com.begumyolcu.urunlerroomapp.room.UrunlerDatabase


class UrunEkleFragment : Fragment() {
    private lateinit var binding: FragmentUrunEkleBinding
    private lateinit var urunDB: UrunlerDatabase
    private lateinit var urunViewModel: UrunViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_urun_ekle, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = UrunlerDatabase.getUrunlerDatabase(application)?.urunlerDao

        val viewModelFactory = dataSource?.let { UrunViewModelFactory(it, application) }
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

        binding.apply {
            buttonUrunEkle.setOnClickListener {
                val urunAdInput = editTextUrunAd.text.toString()
                val urunFiyatInput = editTextUrunFiyat.text.toString().toDouble()
                val urunAdetInput = ediTextUrunAdet.text.toString().toInt()

                urunViewModel.ekleUrun(
                    UrunModel(
                        urunAd = urunAdInput,
                        urunAdet = urunAdetInput,
                        urunFiyat = urunFiyatInput
                    )
                )

                findNavController().navigate(R.id.urunEkleToAnasayfa)
            }


        }
    }
}