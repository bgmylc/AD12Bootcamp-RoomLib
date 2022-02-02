package com.begumyolcu.urunlerroomapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.begumyolcu.urunlerroomapp.databinding.FragmentAnasayfaBinding
import com.begumyolcu.urunlerroomapp.room.UrunModel
import com.begumyolcu.urunlerroomapp.room.UrunlerDatabase
import com.google.android.material.snackbar.Snackbar

class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var urunList: List<UrunModel>
    private lateinit var urunDB: UrunlerDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        urunDB = UrunlerDatabase.getUrunlerDatabase(requireContext())!!
        urunList = urunDB.urunlerDao.tumUrunler()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tumUrunleriGoster()

        binding.apply {
            buttonYeniUrun.setOnClickListener {
                findNavController().navigate(R.id.anasayfaToUrunEkle)
            }
        }
    }

    fun tumUrunleriGoster() {
        binding.apply {
            if (urunList.isEmpty()) {
                Snackbar.make(requireView(), "Ürün bulunamadı", 1000).show()
            } else {
                val urunlerAdapter = UrunlerAdapter(urunList)
                rvUrun.adapter = urunlerAdapter
                rvUrun.layoutManager = GridLayoutManager(context,2)
                rvUrun.setHasFixedSize(true)
            }
        }
    }

}