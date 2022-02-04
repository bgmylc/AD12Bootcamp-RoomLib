package com.begumyolcu.urunlerroomapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var urunViewModel: UrunViewModel
    private lateinit var adapter: UrunlerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = UrunlerDatabase.getUrunlerDatabase(application)?.urunlerDao

        val viewModelFactory = dataSource?.let{ UrunViewModelFactory(it, application)}
        urunViewModel = viewModelFactory?.let {
            ViewModelProvider(this, it).get(UrunViewModel::class.java)
        }!!

        urunViewModel.urunlerList.observe(viewLifecycleOwner){ urunlerList ->
            urunList = urunlerList
            adapter = UrunlerAdapter(urunList)
            binding.adapter = adapter
        }
        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        urunDB = UrunlerDatabase.getUrunlerDatabase(requireContext())!!
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
        urunViewModel.urunlerList.observe(viewLifecycleOwner) { urunlerList ->
            urunList = urunlerList
            binding.apply {
                if (urunList.isEmpty()) {
                    Snackbar.make(requireView(), "Ürün bulunamadı", 1000).show()
                } else {
                    rvUrun.layoutManager = GridLayoutManager(context,2)
                    rvUrun.setHasFixedSize(true)
                }
            }
        }

    }

}