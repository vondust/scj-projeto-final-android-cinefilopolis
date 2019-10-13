package br.com.brunoaguiar.cinefilopolis.view.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.brunoaguiar.cinefilopolis.api.MovieRepository
import br.com.brunoaguiar.cinefilopolis.model.Movie

class FormMovieViewMode (
    val movieRepository: MovieRepository
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()
    fun updateMovie(movie: Movie) {
        isLoading.value = true
        movieRepository.updateMovie(
            movie = movie,
            onComplete = {
                isLoading.value = false
                messageResponse.value = "Dados atualizados com sucesso"
            },
            onError = {
                isLoading.value = false
                messageResponse.value = it.message
            }
        )
    }
}