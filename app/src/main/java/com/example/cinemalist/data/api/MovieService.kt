package com.example.cinemalist.data.api

import com.example.cinemalist.models.Item
import com.example.cinemalist.models.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/constanta-android-dev/intership-wellcome-task/main/films.json")
    fun getMovieList(): Response<Movie>
}