package br.com.brunoaguiar.cinefilopolis.api

import br.com.brunoaguiar.cinefilopolis.model.HealthResponse
import br.com.brunoaguiar.cinefilopolis.model.Movie
import br.com.brunoaguiar.cinefilopolis.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl(val movieService: MovieService) : MovieRepository {

    override fun checkHealth(onComplete: () -> Unit, onError: (Throwable?) -> Unit) {
        movieService.checkHealth()
            .enqueue(object : Callback<HealthResponse> {
                override fun onFailure(call: Call<HealthResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<HealthResponse>,
                    response: Response<HealthResponse>
                ) {
                    onComplete()
                }
            })
    }

    override fun getMovies(
        size: Int, sort: String,
        onComplete: (List<Movie>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        movieService.getMovies(size, sort)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<MovieResponse>, response:
                    Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body()?.content)
                    } else {
                        onError(Throwable("Não foi possível carregar os Filmes"))
                    }
                }
            })
    }

    override fun updateMovie(
        movie: Movie, onComplete: (Movie?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        movieService
            .updateMovie(movie)
            .enqueue(object : Callback<Movie> {
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Não foi possível realizar a requisição"))
                    }
                }
            })
    }
}