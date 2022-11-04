package com.raihan.mobileappdemo.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.raihan.mobileappdemo.MainActivity
import com.raihan.mobileappdemo.R
import com.raihan.mobileappdemo.room.MovieRoomModel
import com.raihan.mobileappdemo.room.RoomViewModel
import java.lang.Exception
import java.util.*

class MovieDetails : AppCompatActivity() {
    private lateinit var roomViewModel: RoomViewModel
    private lateinit var toolbar: Toolbar
    private lateinit var image_icon: ImageView
    private lateinit var movie_name: TextView
    private lateinit var movie_rating: TextView
    private lateinit var description_value: TextView
    private lateinit var movie_genere: TextView
    private lateinit var movie_release_date: TextView
    private lateinit var btn_tailar: TextView
    private lateinit var btn_watchlist: TextView
    private lateinit var btn_removelist: TextView
    var title = ""
    var rating = ""
    var trailerLink = ""
    var description = ""
    var genre = ""
    var releasedDate = ""
    var movieId = ""
    var duration = ""
    var sl = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)

        toolbar = findViewById(R.id.toolbar)
        image_icon = findViewById(R.id.image_icon)
        movie_name = findViewById(R.id.movie_name)
        movie_rating = findViewById(R.id.movie_rating)
        description_value = findViewById(R.id.description_value)
        movie_genere = findViewById(R.id.movie_genere)
        movie_release_date = findViewById(R.id.movie_release_date)
        btn_tailar = findViewById(R.id.btn_tailar)
        btn_watchlist = findViewById(R.id.btn_watchlist)
        btn_removelist = findViewById(R.id.btn_removelist)

        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Movie Details"

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        title = intent.getStringExtra("title").toString()
        movieId = intent.getStringExtra("movieId").toString()
        rating = intent.getStringExtra("rating").toString()
        trailerLink = intent.getStringExtra("trailerLink").toString()
        description = intent.getStringExtra("description").toString()
        genre = intent.getStringExtra("genre").toString()
        releasedDate = intent.getStringExtra("releasedDate").toString()
        duration = intent.getStringExtra("duration").toString()

        movie_name.text = title
        movie_rating.text = rating
        description_value.text = description
        movie_genere.text = genre
        movie_release_date.text = releasedDate



        //  imageId.let { image_icon.setImageResource(it) }

        if (movieId.equals("TE")) {
            image_icon.setImageResource(R.drawable.tenet)
        } else if (movieId.equals("SM")) {
            image_icon.setImageResource(R.drawable.spiderman)
        } else if (movieId.equals("KO")) {
            image_icon.setImageResource(R.drawable.knives)
        } else if (movieId.equals("GG")) {
            image_icon.setImageResource(R.drawable.guardians)
        } else if (movieId.equals("AU")) {
            image_icon.setImageResource(R.drawable.avengers)
        } else {
            image_icon.setImageResource(R.drawable.tenet)
        }


        roomViewModel.readSingle(movieId.trim())
            .observe(this) { optionInfo ->

                try {
                    sl = optionInfo.sl
                    if (optionInfo.watchFlag.equals("Y")) {
                        btn_removelist.visibility = View.VISIBLE
                        btn_watchlist.visibility = View.GONE
                    } else {
                        btn_removelist.visibility = View.GONE
                        btn_watchlist.visibility = View.VISIBLE

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

        btn_tailar.setOnClickListener {
            goToUrl(trailerLink, this)
        }

        btn_watchlist.setOnClickListener {
            val movieRoomModel =
                MovieRoomModel(
                    0,
                    movieId,
                    title,
                    description,
                    rating,
                    duration,
                    genre,
                    releasedDate,
                    trailerLink,
                    "Y"
                )
            roomViewModel.insertData(movieRoomModel)
        }

        btn_removelist.setOnClickListener {
            val movieRoomModel =
                MovieRoomModel(
                    sl,
                    movieId,
                    title,
                    description,
                    rating,
                    duration,
                    genre,
                    releasedDate,
                    trailerLink,
                    "N"
                )
            roomViewModel.updateData(movieRoomModel)
        }


    }

    fun goToUrl(url: String, activity: Activity) {
        val uriUrl = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        activity.startActivity(launchBrowser)
    }
}