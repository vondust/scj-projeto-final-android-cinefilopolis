package br.com.brunoaguiar.cinefilopolis.model

data class Movie(
    val title: String = "Título",
    val synopsis: String = "sinopse",
    val duration: String = "duracao",
    val review: String = "avaliacao",
    val rating: Double = 0.0,
    val situation: String = "A assistir"
)