package com.example.cinemalist.ui.first

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemalist.data.api.MovieRepository
import com.example.cinemalist.models.Movie
import com.example.cinemalist.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(private val repository: MovieRepository): ViewModel() {

    val movieLiveData: MutableLiveData<Resource<Movie>> = MutableLiveData()
    init {
        getItem()
    }

    private fun getItem() = viewModelScope.launch {
        movieLiveData.postValue(Resource.Loading())
        val responce = repository.getMovie()
        if (responce.isSuccessful){
            responce.body().let { res->
                movieLiveData.postValue(Resource.Success(res))
            }
        } else {
            movieLiveData.postValue(Resource.Error(message = responce.message()))
        }
    }
}
