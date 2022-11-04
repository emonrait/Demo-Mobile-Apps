package com.raihan.mobileappdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.raihan.mobileappdemo.adaptar.MenuAdapter
import com.raihan.mobileappdemo.model.Menu
import com.raihan.mobileappdemo.view.AboutMe
import com.raihan.mobileappdemo.view.MovieList
import com.raihan.mobileappdemo.view.WorkList
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var menuList: ArrayList<Menu>
    private lateinit var menuGridView: GridView
    var adapter: MenuAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menuGridView = findViewById(R.id.menuGridView)
        menuList = ArrayList<Menu>()

        menuList.add(Menu("ML", "Movie List", R.drawable.playlist))
        menuList.add(Menu("SW", "Some of My Work", R.drawable.playlist))
        menuList.add(Menu("AM", "About Me", R.drawable.ic_programmer))

        adapter = MenuAdapter(this, menuList)
        menuGridView.adapter = adapter

        menuGridView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val menu_soft_code =
                    view.findViewById<View>(R.id.menu_soft_code) as TextView
                val menu_name =
                    view.findViewById<View>(R.id.menu_name) as TextView

                when {
                    "ML" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, MovieList::class.java)
                        startActivity(intent)
                    }
                    "SW" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, WorkList::class.java)
                        startActivity(intent)
                    }
                    "AM" == menu_soft_code.text.toString() -> {
                        val intent = Intent(this, AboutMe::class.java)
                        startActivity(intent)
                    }

                }
            }
    }
}