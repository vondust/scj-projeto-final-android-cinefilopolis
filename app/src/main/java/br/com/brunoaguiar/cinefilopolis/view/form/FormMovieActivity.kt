package br.com.brunoaguiar.cinefilopolis.view.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.brunoaguiar.cinefilopolis.R
import br.com.brunoaguiar.cinefilopolis.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_form_movie.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FormMovieActivity : AppCompatActivity() {

    val formMovieViewModel: FormMovieViewMode by viewModel()
    val picasso: Picasso by inject()
    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_movie)
        setValues()
        formMovieViewModel.messageResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        btSaveMovie.setOnClickListener {
            movie.title = inputTitle.text.toString()
            movie.synopsis = inputSynopsis.text.toString()
            movie.duration = inputDuration.text.toString()
            movie.review = inputReview.text.toString()
            movie.rating = inputRating.text.toString().toDouble()
            movie.situation = inputStatus.text.toString()

            formMovieViewModel.updateMovie(movie)
        }
    }

    private fun setValues() {
        movie = intent.getParcelableExtra<Movie>("MOVIE")

        val editText: EditText = findViewById(R.id.inputTitle)
        editText.setText(movie.title)

        val editText2: EditText = findViewById(R.id.inputSynopsis)
        editText2.setText(movie.synopsis)

//        inputTitle = findViewById(R.id.inputTitle).setText(movie.title)
//        inputSynopsis.text = movie.synopsis
//        inputDuration.text = movie.duration
//        inputReview.text = movie.review
//        inputRating.text = movie.rating
//        inputStatus.text = movie.situation

//        ((inputTitle)findViewById(R.id.inputTitle)).setText(movie.title.toString());

    }

    private fun setListener(seekBar: SeekBar, textView: TextView) {
        seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?, progress: Int, fromUser:
                Boolean
            ) {
                textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
