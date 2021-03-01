package com.briggin.average.property.blockchainrbiggin.data

import com.google.gson.annotations.SerializedName

data class DogDto(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)
