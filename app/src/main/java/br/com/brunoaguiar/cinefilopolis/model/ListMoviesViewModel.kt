package br.com.brunoaguiar.cinefilopolis.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.brunoaguiar.cinefilopolis.api.MovieRepository

class ListMoviesViewModel (val movieRepository: MovieRepository) : ViewModel() {
    val messageError: MutableLiveData<String> = MutableLiveData()
    val movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    fun getMovies() {
        isLoading.value = true
        movieRepository.getMovies(
            150, "number,asc", {
                movies.value = it
                messageError.value = ""
                isLoading.value = false
            }, {
                movies.value = emptyList()
                messageError.value = it?.message
                isLoading.value = false
            }
        )
    }
}