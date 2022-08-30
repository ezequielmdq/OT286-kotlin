package com.melvin.ongandroid.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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

    private lateinit var welcomeImagesAdapter: ListAdapter

    private val viewModel: OngViewModel by activityViewModels(
        factoryProducer = {
            OngViewModelFactory(
                WelcomeDataRepository(),
                NovedadDataRepository(),
                TestimonioDataRepository()
            )
        }

    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //inflo en binding
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        //inicializo observadores
        configObservers()

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
                    configWelcomeImages(welcomeImages)
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
                    configNovedades(novedades)
                }
            })

            //observador del liveData "error" que me avisara cuando haya un error
            error.observe(viewLifecycleOwner, Observer { error ->
                error?.let {
                    onLoadError()
                }
            })

            // observador cuando se falla los tres servicios
       errorMassiva.observe(viewLifecycleOwner, Observer {  errorMassiva ->
                errorMassiva.let {
                    checkErrorMassiva()
                }
            })

        }
    }
    // funcion para el spinner en la seccion de inicio
    private fun progressBarVisibility(show: Boolean){
        with(binding){
          binding?.spLoading?.progressBar?.visibility = if (show)View.VISIBLE else View.GONE

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

    private fun configWelcomeImages(welcomeImages: List<WelcomeImage>) {
        //instancio el adapter
        welcomeImagesAdapter = ListAdapter()
        //seteo la lista al adapter
        welcomeImagesAdapter.list.addAll(welcomeImages)


        //seteo el adapter al recyclerView "Binenvenidos"
        binding?.let { binding ->
            binding.rvWelcome.adapter = welcomeImagesAdapter
        }
    }

    /**
     * configura la lista de novedades
     */
    private fun configNovedades(novedades: List<Novedad>) {
        novedadAdapter = NovedadAdapter()
        binding?.let { binding ->
            binding.rvNovedades.apply {
                adapter = novedadAdapter
            }
        }

        novedadAdapter.actualizarData(novedades)
    }


    /**
     * crea un snackBar presentando un mensaje de error y da la opcion de reintentar cargar
     */
    fun onLoadError() {
        binding?.let {
            Snackbar
                .make(it.root, resources.getString(R.string.retry), Snackbar.LENGTH_INDEFINITE)
                .setAction("Reintentar") {
                    //el boton reintentar ejecutara el metodo "retry" implementado en el viewModel
                    viewModel.retry()

                }
                .show()
        }
    }

    // Usar esta Funcion spinner cada vez estamos cargando datos

    private fun progressBarLoading(show: Boolean) {
        with(binding) {
            binding?.spinnerCarga1?.spinnerApi?.visibility = if (show) View.VISIBLE else View.GONE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}
    









