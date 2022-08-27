package com.melvin.ongandroid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.melvin.ongandroid.model.Novedad
import com.melvin.ongandroid.model.data.NovedadData
import com.melvin.ongandroid.model.data.NovedadDataRepositorio
import com.melvin.ongandroid.model.data.WelcomeDataRepositorio
import com.melvin.ongandroid.model.data.WelcomeImage
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class OngViewModelTest{

    @RelaxedMockK
    private lateinit var repositoryWelcome : WelcomeDataRepositorio

    @RelaxedMockK
    private lateinit var repositoryNovedad : NovedadDataRepositorio

    private lateinit var ongViewModel: OngViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * Aca se prepara la configuracion inicial para hacer los tests, esto se ejecuta antes de los tests
     */
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        ongViewModel = OngViewModel(repositoryWelcome, repositoryNovedad)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    /**
     * Este metodo se ejecuta luego de los tests y es para resetear el Dispatcher que nos sirvio para trabajar con corutinas
     */
    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }


    /** CASO EXITO
     * Este test comprueba que cada vez que se llama al metodo que hace la consulta a la api en repositorio
     *  y este trae la lista, esta se guarda efectivamente en el LiveData
     */
    @Test
    fun `when getDataSlide return a list of images set on the LiveData`() = runTest{
    //Given
        val list = listOf(WelcomeImage("example.png", "Imagen 1", "Descripcion 1"))
        coEvery {repositoryWelcome.getDataSlide()} returns list

    //When
        ongViewModel.loadSlide()

    //Then
        assert(ongViewModel.listaSlide.value == list)

    }

    /** CASO ERROR
     * Este test comprueba que cada vez que se llama al metodo que hace la consulta a la api en repositorio
     *  y este falla trayendo una lista vacia, esta se guarda efectivamente en el LiveData
     */
    @Test
    fun `when getDataSlide return a empty list set on the LiveData`(){

        val emptyList = emptyList<WelcomeImage>()

        //Given
        coEvery { repositoryWelcome.getDataSlide() } returns emptyList()
        //When
        ongViewModel.loadSlide()

        //Then
        assert(emptyList == ongViewModel.listaSlide.value)

    }

    /** CASO ERROR
     * Este test comprueba que cada vez que se llama al metodo que hace la consulta a la api en repositorio
     *  y este trae un null, en el LiveData se deberia guardar una lista vacia.
     */
    @Test
    fun `if getDataSlide return a null set a empty list on the LiveData`(){
        val emptyList = emptyList<WelcomeImage>()

        //Given
        coEvery { repositoryWelcome.getDataSlide() } returns null
        //When
        ongViewModel.loadSlide()

        //Then
        assert(emptyList == ongViewModel.listaSlide.value)
    }


    /** CASO EXITO
     * Este test comprueba que cada vez que se llama al metodo que hace la consulta a la api en repositorio
     *  y este trae la lista con valores, esta se guarda efectivamente en el LiveData
     */
    @Test
    fun `when getDataNovedad return a list of images set on the LiveData`() = runTest{
        //Given
        val list = listOf(Novedad("example.png", "Imagen 1", "Descripcion 1"))
        coEvery {repositoryNovedad.getDataNovedad()} returns list

        //When
        ongViewModel.loadNovedades()

        //Then
        assert(ongViewModel.listaNovedad.value == list)

    }

    /** CASO ERROR
     * Este test comprueba que cada vez que se llama al metodo que hace la consulta a la api en repositorio
     *  y este falla trayendo una lista vacia, esta se guarda efectivamente en el LiveData
     */
    @Test
    fun `when getDataNovedad return a empty list set on the LiveData`(){

        val emptyList = emptyList<Novedad>()

        //Given
        coEvery { repositoryNovedad.getDataNovedad() } returns emptyList()
        //When
        ongViewModel.loadNovedades()

        //Then
        assert(emptyList == ongViewModel.listaNovedad.value)

    }

    /** CASO ERROR
     * Este test comprueba que cada vez que se llama al metodo que hace la consulta a la api en repositorio
     *  y este trae un null, en el LiveData se deberia guardar una lista vacia.
     */
    @Test
    fun `if getDataNovedad return a null set a empty list on the LiveData`(){
        val emptyList = emptyList<Novedad>()

        //Given
        coEvery { repositoryNovedad.getDataNovedad() } returns null
        //When
        ongViewModel.loadNovedades()

        //Then
        assert(emptyList == ongViewModel.listaNovedad.value)
    }





}