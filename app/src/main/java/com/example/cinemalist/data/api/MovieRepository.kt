package com.example.cinemalist.data.api

import com.example.cinemalist.models.Item
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieService: MovieService) {

    fun getMovie() = movieService.getMovieList()
}