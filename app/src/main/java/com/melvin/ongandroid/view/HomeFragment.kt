package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.melvin.ongandroid.R
import com.melvin.ongandroid.bindTestimonio
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.model.Testimonio
import com.melvin.ongandroid.model.Novedad
import com.melvin.ongandroid.model.AlkemyAPIClient
import com.melvin.ongandroid.model.data.WelcomeImage
import com.melvin.ongandroid.viewmodel.OngViewModel
import com.melvin.ongandroid.viewmodel.OngViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private lateinit var novedadAdapter: NovedadAdapter
    private var adapter = ListAdapter()
    private var dataslide = mutableListOf<WelcomeImage>()
    private val viewModel : OngViewModel by activityViewModels(
        factoryProducer = { OngViewModelFactory() }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        configWelcomeList()
        configTestimonios()
        configObservers()


        iniciarRecyclerViewNovedades()
        crearYCargarListaNovedades()
        onLoadError(" ", { Unit })

        cargaListaSlide()
        cargaListaNovedades()

        return binding?.root
    }


    /**
     * descarga la lista de testimonio y presenta en la cardview de testimonios
     */
    private fun configTestimonios() {
        val list = listOf(
            Testimonio(
                "Jose",
                "Lo mejor",
                " Todos en el Privilege Club se esmeraron y nos mimaron. Hemos recibido de ellos el mejor de los tratos y la mejor voluntad para solucionar unos pequeños problemas personales. Gustavo, Carlos, José y Morgan nos consintieron, junto a Lorena y el resto de los chicos del bar.",
                "https://loremflickr.com/320/240/dog"
            ),
            Testimonio("Gaston", "Lo Peorsito", "  bla bla", "https://loremflickr.com/320/240"),
            Testimonio(
                "Mica",
                "Majomeno",
                "Como estan bla bla",
                "https://loremflickr.com/g/320/240/paris"
            ),
            Testimonio(
                "Nose",
                "Ahi",
                "Como estan bla bla",
                "https://loremflickr.com/320/240/brazil,rio"
            ),
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
    private fun configWelcomeList() {
        val list = listOf(
            WelcomeImage(
                "https://loremflickr.com/320/240",
                "Hola",
                "Todos en el Privilege Club se esmeraron y nos mimaron. Hemos recibido de ellos el mejor de los tratos y la mejor voluntad para solucionar unos pequeños problemas personales. Gustavo, Carlos, José y Morgan nos consintieron, junto a Lorena y el resto de los chicos del bar."
            ),
            WelcomeImage(
                "https://loremflickr.com/320/240/dog",
                "Hola",
                "Como estan bla bla"
            ),
            WelcomeImage(
                "https://loremflickr.com/g/320/240/paris",
                "Hola",
                "Como estan bla bla"
            ),
            WelcomeImage(
                "https://loremflickr.com/320/240/brazil,rio",
                "Hola",
                "Como estan bla bla"
            ),
            WelcomeImage(
                "https://loremflickr.com/g/320/240/paris,girl/all",
                "Hola",
                "Como estan bla bla"
            )
        )


        //instancio el adapter
        val adapter = ListAdapter()
        //seteo la lista al adapter
        adapter.list.addAll(list)
        //seteo el adapter al recyclerView "Binenvenidos"
        binding?.let { binding ->
            binding.rvWelcome.adapter = adapter
        }
    }

    //Configuracion del Recycler view
    private fun iniciarRecyclerViewNovedades() {
        novedadAdapter = NovedadAdapter()
        binding?.let { binding ->
            binding.rvNovedades.apply {
                adapter = novedadAdapter
            }
        }
    }

    //Carga las listas con imagenes random
    private fun crearYCargarListaNovedades() {
        val novedades = listOf(
            Novedad("Novedad 1", "https://loremflickr.com/320/240", "descripcion 1"),
            Novedad("Novedad 2", "https://loremflickr.com/320/240/dog", "descripcion 2"),
            Novedad("Novedad 3", "https://loremflickr.com/g/320/240/paris", "descripcion 3"),
            Novedad("Novedad 4", "https://loremflickr.com/g/320/240/roma", "descripcion 4")
        )

        novedadAdapter.actualizarData(novedades)

    }

    // Llamado a servicio Retrofit


    fun loadSlide() {
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

        fun onLoadError(message: String, retryRecycler: () -> Unit) {
            binding?.let {
                Snackbar.make(it.root, message, Snackbar.LENGTH_INDEFINITE)
                    .setAction(resources.getString(R.string.retry)) { retryRecycler() }
                    .show()
            }
        }
        fun configLists() {


        }


        override fun onDestroyView() {
            super.onDestroyView()
            binding = null
        }


    // Carga la lista en recycler de slider
    private fun cargaListaSlide(){

        binding?.let { binding ->
            binding.rvWelcome.adapter = adapter
        }

        viewModel.loadSlide()

        viewModel.listaSlide.observe(viewLifecycleOwner){try {


           viewModel.listaSlide.value?.let { it1 -> adapter.loadDataSlide(it1) }
        }catch (e : Exception){
            adapter.loadDataSlide(emptyList())
        } }
    }

    // Carga la lista en recycler de novedad

    private fun cargaListaNovedades(){

        binding?.let { binding ->
            binding.rvNovedades.apply { adapter = novedadAdapter
            }
        }

        viewModel.loadNovedades()

                viewModel.listaNovedad.observe(viewLifecycleOwner) {try {

                  viewModel.listaNovedad.value?.let { it1 -> novedadAdapter.actualizarData(it1) }
                }catch (e : Exception){
                      novedadAdapter.actualizarData(emptyList())
                 }
                }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        binding = null


    }


