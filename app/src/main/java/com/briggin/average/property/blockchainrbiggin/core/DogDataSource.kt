package com.briggin.average.property.blockchainrbiggin.core

interface DogDataSource {

    suspend fun getThatDog(): DogDomain
}

sealed class DogDomain{
    data class Success(val url: String): DogDomain()
    object Error: DogDomain()
}