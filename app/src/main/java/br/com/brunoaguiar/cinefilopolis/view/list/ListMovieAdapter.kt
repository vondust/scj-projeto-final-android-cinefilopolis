package br.com.brunoaguiar.cinefilopolis.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.brunoaguiar.cinefilopolis.R
import br.com.brunoaguiar.cinefilopolis.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

class ListMovieAdapter(
    val movies: List<Movie>,
    val picasso: Picasso,
    val clickListener: (Movie) -> Unit
) : RecyclerView.Adapter<ListMovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_list_item,
            parent, false
        )
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindView(movie, clickListener)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            movie: Movie,
//            picasso: Picasso,
            clickListener: (Movie) -> Unit
        ) = with(itemView) {
            movie_title.text = movie.title
            movie_status.text = movie.situation
//            picasso.load("https://pokedexdx.herokuapp.com${pokemon.urlImagem}").into(
//                ivPokemon
//            )
            setOnClickListener { clickListener(movie) }
        }
    }
}
