package com.raihan.mobileappdemo.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raihan.mobileappdemo.MainActivity
import com.raihan.mobileappdemo.R
import com.raihan.mobileappdemo.adaptar.MovieListAdaptar
import com.raihan.mobileappdemo.model.Movie
import com.raihan.mobileappdemo.room.RoomViewModel
import java.util.*

class MovieList : AppCompatActivity() {
    private lateinit var roomViewModel: RoomViewModel
    private lateinit var movieList: ArrayList<Movie>
    private lateinit var adapter: MovieListAdaptar
    private lateinit var movieRecyler: RecyclerView
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

        movieRecyler = findViewById(R.id.movieRecyler)
        toolbar = findViewById(R.id.toolbar)
        movieList = ArrayList<Movie>()
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Movie List"
        roomViewModel.readSingle("SM").observe(this) { optionInfo ->

//            Log.d("value-->", optionInfo.watchFlag)
        }

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        movieList.add(
            Movie(
                "Tenet",
                "Description: Armed with only one word, Tenet, and fighting for the survival of the entire world, a" +
                        "Protagonist journeys through a twilight world of international espionage on a mission that will" +
                        "unfold in something beyond real time.",
                "7.8",
                "2h 30 min",
                "Action, Sci-Fi",
                "3 September 2020",
                "https://www.youtube.com/watch?v=LdOM0x0XDMo",
                R.drawable.tenet,
                "TE"
            )
        )

        movieList.add(
            Movie(
                "Spider-Man: Into the Spider-Verse",
                "Teen Miles Morales becomes the Spider-Man of his universe, and must join with five " +
                        "spider-powered individuals from other dimensions to stop a threat for all realities.",
                "8.4",
                "1h 57min",
                " Action, Animation, Adventure",
                "14 December 2018",
                "https://www.youtube.com/watch?v=tg52up16eq0",
                R.drawable.spiderman,
                "SM"
            )
        )
        movieList.add(
            Movie(
                "Knives Out",
                "Teen Miles Morales becomes the Spider-Man of his universe, and must join with five " +
                        "spider-powered individuals from other dimensions to stop a threat for all realities.",
                "8.4",
                "1h 57min",
                " Action, Animation, Adventure",
                "14 December 2018",
                "https://www.youtube.com/watch?v=tg52up16eq0",
                R.drawable.knives,
                "KO"
            )
        )
        movieList.add(
            Movie(
                "Guardians of the Galaxy",
                "A group of intergalactic criminals must pull together to stop a fanatical warrior with" +
                        "plans to purge the universe.",
                "8.0",
                "2h 1min",
                "Action, Adventure, Comedy",
                "1 August 2014",
                "https://www.youtube.com/watch?v=d96cjJhvlMA",
                R.drawable.guardians,
                "GG"
            )
        )
        movieList.add(
            Movie(
                "Avengers: Age of Ultron",
                "When Tony Stark and Bruce Banner try to jump-start a dormant peacekeeping" +
                        "program called Ultron, things go horribly wrong and it's up to Earth's mightiest heroes to stop the" +
                        "villainous Ultron from enacting his terrible plan.",
                "7.3",
                "2h 21min",
                "Action, Adventure, Sci-Fi",
                "1 May 2015",
                "https://www.youtube.com/watch?v=tmeOjFno6Do",
                R.drawable.avengers,
                "AU"
            )
        )
        adapter = MovieListAdaptar(
            movieList, this, this,
            object : MovieListAdaptar.OnItemClickListener {
                override fun onItemClick(item: Movie?) {
                    // Toast.makeText(this@MovieList, item!!.title, Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MovieList, MovieDetails::class.java)
                    intent.putExtra("title", item!!.title)
                    intent.putExtra("description", item.description)
                    intent.putExtra("rating", item.rating)
                    intent.putExtra("trailerLink", item.trailerLink)
                    intent.putExtra("genre", item.genre)
                    intent.putExtra("releasedDate", item.releasedDate)
                    intent.putExtra("movieId", item.movieId)
                    intent.putExtra("duration", item.duration)
                    startActivity(intent)
                }
            })
        movieRecyler.layoutManager = LinearLayoutManager(applicationContext)
        movieRecyler.adapter = adapter
        movieRecyler.setHasFixedSize(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort -> {
                Collections.sort(movieList,
                    Comparator<Movie> { lhs, rhs -> lhs.title!!.compareTo(rhs.title.toString()) })

                adapter.notifyDataSetChanged()

                Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}