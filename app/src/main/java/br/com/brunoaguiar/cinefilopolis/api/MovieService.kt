package br.com.brunoaguiar.cinefilopolis.api

import br.com.brunoaguiar.cinefilopolis.model.HealthResponse
import br.com.brunoaguiar.cinefilopolis.model.Movie
import br.com.brunoaguiar.cinefilopolis.model.MovieResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface MovieService {
    @GET("/api/pokemon/health")
    fun checkHealth(): Call<HealthResponse>

    @GET("/api/movie")
    fun getMovies(
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): Call<MovieResponse>

    @PUT("/api/movie")
    fun updateMovie(
        @Body pokemon: Movie
    ) : Call<Movie>
}