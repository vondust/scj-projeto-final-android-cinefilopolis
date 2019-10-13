package br.com.brunoaguiar.cinefilopolis.view.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.brunoaguiar.cinefilopolis.R
import br.com.brunoaguiar.cinefilopolis.model.ListMoviesViewModel
import br.com.brunoaguiar.cinefilopolis.view.form.FormMovieActivity
import br.com.brunoaguiar.cinefilopolis.view.main.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class ListMovieActivity : AppCompatActivity() {

    private val movieListViewModel: ListMoviesViewModel by viewModel()
    private val picasso: Picasso by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        movieListViewModel.getMovies()
//        movieListViewModel.isLoading.observe(this, Observer {
//            if (it == true) {
//
//                containerLoading.visibility = View.VISIBLE
//            } else {
//                containerLoading.visibility = View.GONE
//            }
//        })
        movieListViewModel.messageError.observe(this, Observer {
            if (it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
        movieListViewModel.movies.observe(this, Observer {
            rvMovie.adapter = ListMovieAdapter(it, picasso) {
                val intent = Intent(this, FormMovieActivity::class.java)
                intent.putExtra("POKEMON", it)
                startActivity(intent)
                finish()
            }
            rvMovie.layoutManager = GridLayoutManager(this, 3)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId;
        println(item.itemId)

        if (id == R.id.list_toobar_homepage) {
            println("Entrou em homepage")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.list_toobar_logout) {
            println("Entrou em logout")
            finish()
        }

        return true
    }

}
