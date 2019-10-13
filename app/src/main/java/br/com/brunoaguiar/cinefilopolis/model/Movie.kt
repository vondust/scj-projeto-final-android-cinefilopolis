package br.com.brunoaguiar.cinefilopolis.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var title: String = "Título",
    var synopsis: String = "sinopse",
    var duration: String = "duracao",
    var review: String = "avaliacao",
    var rating: Double = 0.0,
    var situation: String = "A assistir"
) : Parcelable