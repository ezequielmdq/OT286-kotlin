package com.melvin.ongandroid.model.repository.Network.retrofit

import kotlinx.serialization.Serializable


@Serializable
data class RegisterError(
    val message: String,
    val error: RegisterResponseErrorBody
)

@Serializable
data class RegisterResponseErrorBody(
    val name: List<String>? = null,
    val email: List<String>? = null,
    val password: List<String>? = null
)