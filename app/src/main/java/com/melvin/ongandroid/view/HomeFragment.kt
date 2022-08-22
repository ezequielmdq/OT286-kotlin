package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melvin.ongandroid.bindTestimonio
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.model.Testimonio
import com.melvin.ongandroid.model.WelcomeImage

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        configWelcomeList()
        configTestimonios()
        configObservers()
        return binding?.root
    }

    /**
     * descarga la lista de testimonio y presenta en la cardview de testimonios
     */
    private fun configTestimonios() {
        val list = listOf(
            Testimonio("Jose", "Lo mejor", " bla bla", "https://loremflickr.com/320/240/dog"),
            Testimonio("Gaston", "Lo Peorsito", "  bla bla", "https://loremflickr.com/320/240"),
            Testimonio("Mica", "Majomeno", "Como estan bla bla", "https://loremflickr.com/g/320/240/paris"),
            Testimonio("Nose", "Ahi", "Como estan bla bla", "https://loremflickr.com/320/240/brazil,rio"),
        )

        binding?.cardTestimonios?.apply {
            bindTestimonio(cardTestimonio1, list[0])
            bindTestimonio(cardTestimonio2, list[1])
            bindTestimonio(cardTestimonio3, list[2])
            bindTestimonio(cardTestimonio4, list[3])
        }

    }

    /**
     * configura todos los observadores que se asocian al fragment
     */
    private fun configObservers() {
    }

    /**
     * configura configura la lista de Bienvenida
     */
    private fun configWelcomeList(){
        val list = listOf(
            WelcomeImage("https://loremflickr.com/320/240", "Hola", "Como estan bla bla"),
            WelcomeImage("https://loremflickr.com/320/240/dog", "Hola", "Como estan bla bla"),
            WelcomeImage("https://loremflickr.com/g/320/240/paris", "Hola", "Como estan bla bla"),
            WelcomeImage("https://loremflickr.com/320/240/brazil,rio", "Hola", "Como estan bla bla"),
            WelcomeImage("https://loremflickr.com/g/320/240/paris,girl/all", "Hola", "Como estan bla bla")
        )

        val adapter = ListAdapter()
        //lista temporal
        adapter.list.addAll(list)
        binding?.let {
            it.rv.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
