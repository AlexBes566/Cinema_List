package com.example.cinemalist.data.api

import com.example.cinemalist.models.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService) {

    suspend fun getMovie() = withContext(Dispatchers.IO) {
        movieService.getMovieList()
    }
}