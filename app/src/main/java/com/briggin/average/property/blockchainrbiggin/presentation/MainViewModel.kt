package com.briggin.average.property.blockchainrbiggin.presentation

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.briggin.average.property.blockchainrbiggin.core.DogDataSource
import com.briggin.average.property.blockchainrbiggin.core.DogDomain

class MainViewModel(
    private val dataSource: DogDataSource
) : ViewModel() {

    private val _state: MutableLiveData<ViewState> = MutableLiveData()

    val progressBar: LiveData<Int>
        get() = Transformations.map(_state) { if (it is Loading) View.VISIBLE else View.GONE }

    val errorMessage: LiveData<ComponentState>
        get() = Transformations.map(_state) {
            if (it is Error) ComponentState("whoops something went wrong", View.VISIBLE) else ComponentState()
        }

    val dogImage: LiveData<ComponentState>
        get() = Transformations.map(_state) {
            if (it is Success) ComponentState(it.url, View.VISIBLE) else ComponentState()
        }

    suspend fun viewLoaded() {
        _state.postValue(Loading)
        when(val domain = dataSource.getThatDog()) {
            is DogDomain.Success -> _state.postValue(Success(domain.url))
            DogDomain.Error -> _state.postValue(Error)
        }
    }

}

sealed class ViewState
object Loading : ViewState()
object Error : ViewState()
data class Success(val url: String) : ViewState()

data class ComponentState(val payload: String = "", val visibility: Int = View.GONE)
