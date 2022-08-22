package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.model.AlkemyAPIClient
import com.melvin.ongandroid.model.data.WelcomeImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var adapter = ListAdapter()
    private var dataslide = mutableListOf<WelcomeImage>()




    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.rv.adapter = adapter

        loadSlide()
        configLists()
        configObservers()

        return binding.root

    }

    private fun configObservers() {

    }


    // Llamado a servicio Retrofit

    fun loadSlide(){
        CoroutineScope(Dispatchers.Main).launch {
            val servicio = AlkemyAPIClient.getClient().getData().body()
            val data = servicio?.data
                dataslide.clear()
                if (data != null) {
                    dataslide.addAll(data)
                    adapter.loadDataSlide(dataslide)
                }
        }
    }



   private fun configLists(){


   }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
