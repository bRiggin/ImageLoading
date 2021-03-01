package com.briggin.average.property.blockchainrbiggin.data

import retrofit2.http.GET

interface DogApi {

    @GET("random")
    suspend fun getDogImage(): DogDto
}
