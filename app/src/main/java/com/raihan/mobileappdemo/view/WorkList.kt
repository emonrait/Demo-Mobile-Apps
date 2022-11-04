package com.raihan.mobileappdemo.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raihan.mobileappdemo.MainActivity
import com.raihan.mobileappdemo.R
import com.raihan.mobileappdemo.adaptar.MovieListAdaptar
import com.raihan.mobileappdemo.adaptar.WorkListAdaptar
import com.raihan.mobileappdemo.model.Movie
import com.raihan.mobileappdemo.model.Work
import java.util.*

class WorkList : AppCompatActivity() {
    private lateinit var workList: ArrayList<Work>
    private lateinit var adapter: WorkListAdaptar
    private lateinit var workRecyler: RecyclerView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_list)

        workRecyler = findViewById(R.id.workRecyler)
        toolbar = findViewById(R.id.toolbar)
        workList = ArrayList<Work>()
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)?.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Work List"

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }



        workList.add(
            Work(
                "Bank Asia Smart App",
                "Bank Asia has been launched by a group of successful entrepreneurs with recognized standing in the society. The management of the Bank consists of a team led by senior bankers with decades of experience in national and international markets. The senior management team is ably supported by a group of professionals many of whom have exposure in the international market.",
                "https://play.google.com/store/apps/details?id=eraapps.bankasia.bdinternetbanking.apps&hl=en&gl=US",
                R.drawable.user
            )
        )

        workList.add(
            Work(
                "Bank Asia Micromerchant App",
                "Bank Asia has been launched by a group of successful entrepreneurs with recognized standing in the society. The management of the Bank consists of a team led by senior bankers with decades of experience in national and international markets. The senior management team is ably supported by a group of professionals many of whom have exposure in the international market.",
                "https://play.google.com/store/apps/details?id=com.remitone.app.BAExchange.live&hl=en&gl=US",
                R.drawable.user
            )
        )
        workList.add(
            Work(
                "BCB e-Cash",
                "The emerging evolution of e-business nowadays has opened a new alternative channel like a mobile app or browser-based web application in the banking sector which can escalate banking business and make life easy to access banking services. More people than ever are using their mobile devices tab, tablet, laptop, etc. to work, shop, organize, plan and travel, etc. It is high time to make sense for taking advantage of the mobile channel. So, one of the major objectives to introduce BCB e-Cash is to enhance banking business besides conventional branch banking.",
                "https://play.google.com/store/apps/details?id=eraapps.bcbl.bdinternetbanking.apps&hl=en&gl=US",
                R.drawable.user
            )
        )
        workList.add(
            Work(
                "BDBL Digital Banking App",
                "BDBL DIGITAL BANK’ is one of Bangladesh's best and most secured mobile apps. ‘It is a digital financial solution that provides nearly all banking services to its customer in easy and secure ways, in just a few simple steps. By using the ‘BDBL DIGITAL BANK’ Mobile App any user can enjoy the following Services/Features:",
                "https://play.google.com/store/apps/details?id=eraapps.bdbl.bdinternetbanking.apps&hl=en&gl=US",
                R.drawable.user
            )
        )
        workList.add(
            Work(
                "City Bank Agent Banking App",
                "City Bank is one of the first private commercial banks in Bangladesh. It started the journey bank in 1983 and never looked back. Citytouch - Digital Banking Service from City Bank offers the simplest way of doing banking from anywhere round the clock. It offers most of the privileges and conveniences of branch banking on your fingertips. Citytouch supports both English and Bangla language. Use Citytouch ID and Password to login or instantly register just clicking the ‘Sign Up’ option in the app or web.",
                "https://play.google.com/store/apps/details?id=com.thecitybank.citytouch&hl=en&gl=US",
                R.drawable.user
            )
        )

        workList.add(
            Work(
                "Positive Thinkers Unity",
                "Positive Thinkers Unity Finical Apps",
                "https://github.com/emonrait",
                R.drawable.user
            )
        )
        workList.add(
            Work(
                "Dream Builders",
                "Dream Builders Finical Apps",
                "https://github.com/emonrait",
                R.drawable.user
            )
        )

        workList.add(
            Work(
                "Plan of Establishing Together",
                "Plan of Establishing TogetherFinical Apps",
                "https://github.com/emonrait",
                R.drawable.user
            )
        )

        adapter = WorkListAdaptar(
            workList,
            object : WorkListAdaptar.OnItemClickListener {
                override fun onItemClick(item: Work?) {

                    goToUrl(item!!.playLink.toString(), this@WorkList)

                }
            })
        workRecyler.layoutManager = LinearLayoutManager(applicationContext)
        workRecyler.adapter = adapter
        workRecyler.setHasFixedSize(true)
    }

    fun goToUrl(url: String, activity: Activity) {
        val uriUrl = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        activity.startActivity(launchBrowser)
    }
}