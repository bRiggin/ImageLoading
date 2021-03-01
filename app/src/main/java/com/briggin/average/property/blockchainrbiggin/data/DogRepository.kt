package com.briggin.average.property.blockchainrbiggin.data

import com.briggin.average.property.blockchainrbiggin.core.DogDataSource
import com.briggin.average.property.blockchainrbiggin.core.DogDomain

private const val KEY_SUCCESS = "success"

class DogRepository(private val api: DogApi) : DogDataSource {

    override suspend fun getThatDog(): DogDomain =
        try {
            api.getDogImage().domain
        } catch (e: Exception) {
            DogDomain.Error
        }
}

internal val DogDto.domain: DogDomain
    get() = if (status == KEY_SUCCESS) DogDomain.Success(message) else DogDomain.Error