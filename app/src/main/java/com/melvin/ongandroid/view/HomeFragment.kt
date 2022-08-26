package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.melvin.ongandroid.R
import com.melvin.ongandroid.bindTestimonio
import com.melvin.ongandroid.databinding.FragmentHomeBinding
import com.melvin.ongandroid.model.Testimonio
import com.melvin.ongandroid.model.Novedad
import com.melvin.ongandroid.model.WelcomeImage
import com.melvin.ongandroid.model.repository.Network.implement.NovedadDataRepository
import com.melvin.ongandroid.model.repository.Network.implement.TestimonioDataRepository
import com.melvin.ongandroid.model.repository.Network.implement.WelcomeDataRepository
import com.melvin.ongandroid.viewmodel.OngViewModel
import com.melvin.ongandroid.viewmodel.OngViewModelFactory


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null

    private lateinit var novedadAdapter: NovedadAdapter
    private var adapter = ListAdapter()

    private val viewModel: OngViewModel by activityViewModels(
        factoryProducer = {
            OngViewModelFactory(WelcomeDataRepository(),
                                NovedadDataRepository(),
                                TestimonioDataRepository())
        }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        configObservers()
        //onLoadError(" ", { Unit })

        return binding?.root
    }

    /**
     * configura todos los observadores que se asocian al fragment
     */
    private fun configObservers() {

        viewModel.apply {
            //observador a la lista de welcomeImages
            listaSlide.observe(viewLifecycleOwner, Observer { welcomeImages ->
                welcomeImages?.let {
                    configWelcomeList(welcomeImages)
                }
            })

            //observador a la lista de testimonios
            listaTestimonios.observe(viewLifecycleOwner, Observer { testimonios ->
                testimonios?.let {
                    configTestimonios(testimonios)
                }
            })

            //observador a la lista de novedades
            listaNovedad.observe(viewLifecycleOwner, Observer { novedades ->
                novedades?.let {
                    iniciarRecyclerViewNovedades()
                    crearYCargarListaNovedades(novedades)
                }
            })

        }
    }

    /**
     * descarga la lista de testimonio y presenta en la cardview de testimonios
     */
    private fun configTestimonios(testimonios: List<Testimonio>) {
        binding?.let { it ->
            it.cardTestimonios.apply {
                bindTestimonio(cardTestimonio1, testimonios[0])
                bindTestimonio(cardTestimonio2, testimonios[1])
                bindTestimonio(cardTestimonio3, testimonios[2])
                bindTestimonio(cardTestimonio4, testimonios[3])
            }
        }
    }

    /**
     * configura configura la lista de Bienvenida
     */
    private fun configWelcomeList(welcomeImages: List<WelcomeImage>) {
        //instancio el adapter
        val adapter = ListAdapter()
        //seteo la lista al adapter
        adapter.list.addAll(welcomeImages)
        //seteo el adapter al recyclerView "Binenvenidos"
        binding?.let { binding ->
            binding.rvWelcome.adapter = adapter
        }
    }

    /**
     *Configuracion del Recycler view
     */
    private fun iniciarRecyclerViewNovedades() {
        novedadAdapter = NovedadAdapter()
        binding?.let { binding ->
            binding.rvNovedades.apply {
                adapter = novedadAdapter
            }
        }
    }

    /**
     *Carga las listas con imagenes random
     */
    private fun crearYCargarListaNovedades(novedades: List<Novedad>) {
        novedadAdapter.actualizarData(novedades)
    }

    fun onLoadError(message: String, retryRecycler: () -> Unit) {
        binding?.let {
            Snackbar.make(it.root, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(resources.getString(R.string.retry)) {
                    retryRecycler()
                }
                .show()
        }
    }

    // Carga la lista en recycler de slider
    //ya estaba la funcion "configWelcomeList" que hacia esto
    //el observador esta en configObservers
    private fun cargaListaSlide() {

        binding?.let { binding ->
            binding.rvWelcome.adapter = adapter
        }

        /*viewModel.listaSlide.observe(viewLifecycleOwner) {
            try {
                viewModel.listaSlide.value?.let {
                        it1 -> adapter.loadDataSlide(it1)
                }
            } catch (e: Exception) {
                adapter.loadDataSlide(emptyList())
            }
        }*/
    }

    //se supone que las funciones iniciarRecyclerViewNovedades y crearYCargarListaNovedades
    //ya hacian esto
    //el observador lo movi a configObservers
    // Carga la lista en recycler de novedad
    private fun cargaListaNovedades(novedades: List<Novedad>) {

        binding?.let { binding ->
            binding.rvNovedades.apply {
                adapter = novedadAdapter
            }
        }
        novedadAdapter.actualizarData(novedades)

        //el observador lo puse en configObservers//
        /*viewModel.listaNovedad.observe(viewLifecycleOwner) {
            try {
                viewModel.listaNovedad.value?.let {
                        it1 -> novedadAdapter.actualizarData(it1)
                }
            } catch (e: Exception) {
                novedadAdapter.actualizarData(emptyList())
            }
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

