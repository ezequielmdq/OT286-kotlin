package com.melvin.ongandroid.model

import com.google.gson.annotations.SerializedName

data class Miembros (
    @SerializedName("id") val id: Int= 0,
    @SerializedName("name") val name: String?,
    @SerializedName("image")val image: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("facebookUrl") val facebookUrl: String?,
    @SerializedName("linkedinUrl") val linkedinUrl: String?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("deleted_at") val deletedAt: String?,
    @SerializedName("updated_at") val updatedAt: String
        )
