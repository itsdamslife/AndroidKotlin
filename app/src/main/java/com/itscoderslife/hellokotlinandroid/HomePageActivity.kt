package com.itscoderslife.hellokotlinandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.itscoderslife.hellokotlinandroid.R.id.welcome2

class HomePageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        var data = intent.extras

        if (data != null) {

            var welcomeMsg = resources.getString(R.string.welcome2msg) + " " + data.get("user")
            var wm = findViewById<TextView>(R.id.welcome2)

            wm.setTextKeepState(welcomeMsg)

            Toast.makeText(applicationContext, welcomeMsg, Toast.LENGTH_SHORT)
        } else {
            Toast.makeText(applicationContext, R.string.welcome2msg, Toast.LENGTH_SHORT)
        }

    }
}
