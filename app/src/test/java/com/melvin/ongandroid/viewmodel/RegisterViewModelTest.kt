package com.melvin.ongandroid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.melvin.ongandroid.model.Register
import com.melvin.ongandroid.model.repository.Network.interfaces.NewRegisterStatus
import io.mockk.MockKAnnotations
import io.mockk.MockKStubScope
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
internal class RegisterViewModelTest{

    @RelaxedMockK
    private lateinit var newRegisterStatus: NewRegisterStatus

    private lateinit var registerViewModel : RegisterViewModel

    @get : Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        registerViewModel = RegisterViewModel(newRegisterStatus)
        Dispatchers.setMain(Dispatchers.Unconfined)

    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `When the api donst return a value from the database `() = runTest {
        val list = listOf(Register("Nombre", "email@email.com", "password"))
        //Given
            coEvery {newRegisterStatus} returns list
        //When
            registerViewModel.saveNewRegister("Nombre", "email@email.com", "password")
        //Then
        assert(registerViewModel.listaRegister.value == list )
    }


}

private infix fun <T, B> MockKStubScope<T, B>.returns(list: List<Register>) {

}


