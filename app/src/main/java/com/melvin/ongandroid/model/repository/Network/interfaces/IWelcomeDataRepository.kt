package com.melvin.ongandroid.model.repository.Network.interfaces

import com.melvin.ongandroid.model.WelcomeImage

interface IWelcomeDataRepository {
    suspend fun getWellcomeImages(): List<WelcomeImage>?
}