package br.com.brunoaguiar.cinefilopolis.di

import android.content.Context
import br.com.brunoaguiar.cinefilopolis.api.AuthInterceptor
import br.com.brunoaguiar.cinefilopolis.api.MovieRepository
import br.com.brunoaguiar.cinefilopolis.api.MovieRepositoryImpl
import br.com.brunoaguiar.cinefilopolis.api.MovieService
import br.com.brunoaguiar.cinefilopolis.model.ListMoviesViewModel
import br.com.brunoaguiar.cinefilopolis.view.form.FormMovieViewMode
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private fun createNetworkClient(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://cinefilopolis-34scj-vondust.firebaseio.com/Movie.json/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun createOkhttpClientAuth(authInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
    return builder.build()
}

private fun createPicassoAuth(context: Context, okHttpClient: OkHttpClient): Picasso {
    return Picasso
        .Builder(context)
        .downloader(OkHttp3Downloader(okHttpClient))
        .build()
}

val viewModelModule = module {
    viewModel { ListMoviesViewModel(get()) }
    viewModel { FormMovieViewMode(get()) }
}
val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}
val networkModule = module {
    single<Interceptor> { AuthInterceptor() }
    single { createNetworkClient(get()).create(MovieService::class.java) }
    single { createOkhttpClientAuth(get()) }
    single { createPicassoAuth(get(), get()) }
}