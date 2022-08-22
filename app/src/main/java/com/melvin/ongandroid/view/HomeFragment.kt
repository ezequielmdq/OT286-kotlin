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
            Testimonio("Jose", "Lo mejor", " Todos en el Privilege Club se esmeraron y nos mimaron. Hemos recibido de ellos el mejor de los tratos y la mejor voluntad para solucionar unos pequeños problemas personales. Gustavo, Carlos, José y Morgan nos consintieron, junto a Lorena y el resto de los chicos del bar.", "https://loremflickr.com/320/240/dog"),
            Testimonio("Gaston", "Lo Peorsito", "  bla bla", "https://loremflickr.com/320/240"),
            Testimonio("Mica", "Majomeno", "Como estan bla bla", "https://loremflickr.com/g/320/240/paris"),
            Testimonio("Nose", "Ahi", "Como estan bla bla", "https://loremflickr.com/320/240/brazil,rio"),
        )

        binding?.let { it ->
            it.cardTestimonios.apply {
                bindTestimonio(cardTestimonio1, list[0])
                bindTestimonio(cardTestimonio2, list[1])
                bindTestimonio(cardTestimonio3, list[2])
                bindTestimonio(cardTestimonio4, list[3])
            }
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
            WelcomeImage("https://loremflickr.com/320/240", "Hola", "Todos en el Privilege Club se esmeraron y nos mimaron. Hemos recibido de ellos el mejor de los tratos y la mejor voluntad para solucionar unos pequeños problemas personales. Gustavo, Carlos, José y Morgan nos consintieron, junto a Lorena y el resto de los chicos del bar."),
            WelcomeImage("https://loremflickr.com/320/240/dog", "Hola", "Como estan bla bla"),
            WelcomeImage("https://loremflickr.com/g/320/240/paris", "Hola", "Como estan bla bla"),
            WelcomeImage("https://loremflickr.com/320/240/brazil,rio", "Hola", "Como estan bla bla"),
            WelcomeImage("https://loremflickr.com/g/320/240/paris,girl/all", "Hola", "Como estan bla bla")
        )

        //instancio el adapter
        val adapter = ListAdapter()
        //seteo la lista al adapter
        adapter.list.addAll(list)
        //seteo el adapter al recyclerView "Binenvenidos"
        binding?.let {
            it.rv.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
