package com.melvin.ongandroid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.melvin.ongandroid.application.Validator
import com.melvin.ongandroid.model.LogIn
import com.melvin.ongandroid.model.Token
import com.melvin.ongandroid.model.data.LogInData
import com.melvin.ongandroid.model.repository.Network.interfaces.ILogInDataRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LogInViewModelTest {

    @RelaxedMockK
    private lateinit var repositoryLogIn: ILogInDataRepository

    private lateinit var logInViewModel: LogInViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        logInViewModel = LogInViewModel(repositoryLogIn)

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `siempre que se invoque la funcion login y el inicio sea exitoso el liveData _token debe tener una cadena no vacia`() = runTest {
        //Given
        val login = LogIn("j@hgmail.com", "1234jose")
        val loginData = LogInData(true, Token("token..."), "hola")
        coEvery { repositoryLogIn.postLogin(login) } returns loginData

        //When
        logInViewModel.logIn(login)

        //Then
        assert(!logInViewModel.token.value.isNullOrEmpty())
    }

    @Test
    fun `cuando los campos estan correctamente escritos pero la contrasenia es incorrecta al llamar al metodo postLogin entonces el liveData loginError debe setearse en true`() = runTest {
        //Given
        val login = LogIn("j@hgmail.com", "1234jose")
        val loginDataNotSucces = LogInData(false, Token(""), "hubo un error")
        coEvery { repositoryLogIn.postLogin(login) } returns loginDataNotSucces

        //When
        logInViewModel.logIn(login)

        //Then
        assert(logInViewModel.loginError.value != null && logInViewModel.loginError.value!!.validationTest)

    }

    @Test
    fun `cuando el email no es valido el liveData loginError debe ser true`() = runTest {
        //Given
        val email = "j"
        coEvery { Validator.isEmailValid(email) } returns false

        //When
        logInViewModel.inputLoginChange(email, Inputype.Email)

        //Then
        assert(logInViewModel.loginError.value?.validationTest == true)
    }

    @Test
    fun `cuando la contrasenia no es valida el liveData loginError debe ser true`() = runTest {
        //Given
        val password = "j"
        coEvery { Validator.isEmailValid(password) } returns false

        //When
        logInViewModel.inputLoginChange(password, Inputype.Password)

        //Then
        assert(logInViewModel.loginError.value?.validationTest == true)
    }

    @Test
    fun `cuando la contrasenia o el email no es valida el liveData buttonLogin debe ser false`() = runTest {
        //Given
        val email = ""
        val password = ""
        coEvery { Validator.isEmailValid(email) } returns false
        coEvery { Validator.isEmailValid(password) } returns false

        //When
        logInViewModel.inputLoginChange(email, Inputype.Email)
        logInViewModel.inputLoginChange(password, Inputype.Password)

        //Then
        assert(logInViewModel.buttonLogin.value == false)
    }

    @Test
    fun `cuando la contrasenia y el email son validos el liveData buttonLogin debe ser false`() = runTest {
        //Given
        val email = "j@gamil.com"
        val password = "1234ewref"
        coEvery { Validator.isEmailValid(email) } returns true
        coEvery { Validator.isEmailValid(password) } returns true

        //When
        logInViewModel.inputLoginChange(email, Inputype.Email)
        logInViewModel.inputLoginChange(password, Inputype.Password)

        //Then
        assert(logInViewModel.buttonLogin.value == true)
    }
}