package br.com.brunoaguiar.cinefilopolis.api

import br.com.brunoaguiar.cinefilopolis.model.Movie

interface MovieRepository {
    fun checkHealth(
        onComplete: () -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun getMovies(
        size: Int,
        sort: String,
        onComplete: (List<Movie>?) -> Unit,
        onError: (Throwable?) -> Unit
    )

    fun updateMovie(
        movie: Movie,
        onComplete:(Movie?) -> Unit,
        onError:(Throwable) -> Unit
    )
}