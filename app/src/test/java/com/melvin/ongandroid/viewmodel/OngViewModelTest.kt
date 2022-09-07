package com.melvin.ongandroid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.melvin.ongandroid.model.Miembros
import com.melvin.ongandroid.model.Novedad
import com.melvin.ongandroid.model.Testimonio
import com.melvin.ongandroid.model.WelcomeImage
import com.melvin.ongandroid.model.repository.Network.implement.MiembrosDatarepository

import com.melvin.ongandroid.model.repository.Network.implement.NovedadDataRepository
import com.melvin.ongandroid.model.repository.Network.implement.TestimonioDataRepository
import com.melvin.ongandroid.model.repository.Network.implement.WelcomeDataRepository
import com.melvin.ongandroid.view.contacto.ContactoViewModel
import com.melvin.ongandroid.view.contacto.ContactosDto
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

class OngViewModelTest {

    @RelaxedMockK
    private lateinit var repositoryWelcome: WelcomeDataRepository

    @RelaxedMockK
    private lateinit var repositoryNovedad: NovedadDataRepository

    @RelaxedMockK
    private lateinit var repositoryTestimonio: TestimonioDataRepository


    @RelaxedMockK
    private lateinit var repositoryMiembros: MiembrosDatarepository

    @RelaxedMockK
    private lateinit var dato : ContactosDto


    private lateinit var ongViewModel: OngViewModel

    private lateinit var contactoViewModel: ContactoViewModel

    /**
     * Test Contacto Viewmodel
     * */

    @get:Rule
    var rules: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun contactoBefore(){
        MockKAnnotations.init(this)
        contactoViewModel = ContactoViewModel(dato)

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `cuando los campos son completos` () = runTest{
        val contact = ContactosDto("userName", "userEmail@mail.com", "a message")
        coEvery { dato } returns contact

        contactoViewModel.enviarDatos()

        assert(contactoViewModel._spinnervisible.value == false)
    }

    @Test
    fun `cuando los campos no son completos` () = runTest{
        val contact = ContactosDto("", "userEmail@mail.com", "a message")
       //GIVEN
        coEvery { dato } returns contact
        // WHEN
        contactoViewModel.enviarDatos()
        // THEN
        assert(contactoViewModel._spinnervisible.value == true)
    }

    @Test
    fun `cuando los campos no son completos otra vez` () = runTest{
        val contact = ContactosDto("", "", "a message")
        //GIVEN
        coEvery { dato } returns contact
    // WHEN
        contactoViewModel.enviarDatos()
    // THEN
        assert(contactoViewModel._spinnervisible.value == true)
    }

    @Test
    fun `cuando todos los campos no son completos` () = runTest{
        val contact = ContactosDto("", "userEmail@mail.com", "")
        coEvery { dato } returns contact

        contactoViewModel.enviarDatos()

        assert(contactoViewModel._spinnervisible.value == true)
    }



    /**
     * Estas dos reglas son necesarias para que funcionen las corrutinas
     */
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    /**
     * Aca se prepara la configuracion inicial para hacer los tests, esto se ejecuta antes de los tests
     */
    @Before
      fun onBefore(){
        MockKAnnotations.init(this)
        ongViewModel = OngViewModel(repositoryWelcome, repositoryNovedad, repositoryTestimonio, repositoryMiembros)

    }


    /** CASO EXITO
     * Este test comprueba que cada vez que se llama al metodo que hace la consulta a la api en repositorio
     *  y este trae la lista, esta se guarda efectivamente en el LiveData
     */
    @Test
    fun `when getWellcomeImages return a list of images set on the LiveData`() = runTest {
        //Given
        val list = listOf(WelcomeImage("example.png", "Imagen 1", "Descripcion 1"))
        coEvery { repositoryWelcome.getWellcomeImages() } returns list

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
    fun `when getWellcomeImages return a empty list set on the LiveData`() {

        val emptyList = emptyList<WelcomeImage>()

        //Given
        coEvery { repositoryWelcome.getWellcomeImages() } returns emptyList()
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
    fun `if getWellcomeImages return a null set a empty list on the LiveData`() {
        val emptyList = emptyList<WelcomeImage>()

        //Given
        coEvery { repositoryWelcome.getWellcomeImages() } returns null
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
    fun `when getNovedades return a list of images set on the LiveData`() = runTest {
        //Given
        val list = listOf(Novedad("example.png", "Imagen 1", "Descripcion 1", 1))

        coEvery { repositoryNovedad.getNovedades() } returns list


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
    fun `when getNovedades return a empty list set on the LiveData`() {

        val emptyList = emptyList<Novedad>()

        //Given
        coEvery { repositoryNovedad.getNovedades() } returns emptyList()
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
    fun `if getNovedades return a null set a empty list on the LiveData`() {
        val emptyList = emptyList<Novedad>()

        //Given
        coEvery { repositoryNovedad.getNovedades() } returns null
        //When
        ongViewModel.loadNovedades()

        //Then
        assert(emptyList == ongViewModel.listaNovedad.value)
    }


    /** CASO EXITO
     * Este test comprueba que cada vez que se llama al metodo que hace la consulta a la api en repositorio
     *  y este trae la lista, esta se guarda efectivamente en el LiveData
     */
    @Test
    fun `when getTestimonios return a list of images set on the LiveData`() = runTest {
        //Given
        val list = listOf(Testimonio(1, "Testimonio 1", "example.png", "descripcion 1"))
        coEvery { repositoryTestimonio.getTestimonios() } returns list

        //When
        ongViewModel.loadTestimonios()

        //Then
        assert(ongViewModel.listaTestimonios.value == list)

    }

    /** CASO ERROR
     * Este test comprueba que cada vez que se llama al metodo que hace la consulta a la api en repositorio
     *  y este falla trayendo una lista vacia, esta se guarda efectivamente en el LiveData
     */
    @Test
    fun `when getTestimonios return a empty list set on the LiveData`() {

        val emptyList = emptyList<Testimonio>()

        //Given
        coEvery { repositoryTestimonio.getTestimonios() } returns emptyList()
        //When
        ongViewModel.loadTestimonios()

        //Then
        assert(emptyList == ongViewModel.listaTestimonios.value)

    }

    /** CASO ERROR
     * Este test comprueba que cada vez que se llama al metodo que hace la consulta a la api en repositorio
     *  y este trae un null, en el LiveData se deberia guardar una lista vacia.
     */
    @Test
    fun `if getTestimonios return a null set a empty list on the LiveData`() {
        val emptyList = emptyList<WelcomeImage>()

        //Given
        coEvery { repositoryTestimonio.getTestimonios() } returns null
        //When
        ongViewModel.loadTestimonios()

        //Then
        assert(emptyList == ongViewModel.listaTestimonios.value)
    }


    @Test
    fun `when onCreateMiembros recovers a members empty list from  miembrosDataRepository`() = runTest {
          //GIVEN
          val listOfMiembrosDataModel = listOf(Miembros(0,
            "Angie",
            "imagen",
            "descripcion",
            "facebookUrl",
            "linkedinUrl",
             "",
             "",
             ""
            ))
        coEvery { repositoryMiembros.getMiembros() } returns listOfMiembrosDataModel

        //WHEN
        ongViewModel.loadMiembros()

        //THEN
        assert(ongViewModel.listaMiembros.value == listOfMiembrosDataModel)
    }

    @Test
    fun `when getMiembros return a list of images set on the LiveData`() = runTest {
        //Given
        val list = listOf(
            Miembros(
                0,
                "", "", "", "", "",
                "", "", ""
            )
        )
        coEvery { repositoryMiembros.getMiembros() } returns list

        //When
        ongViewModel.loadMiembros()

        //Then
        assert(ongViewModel.listaMiembros.value == list)
    }


}

