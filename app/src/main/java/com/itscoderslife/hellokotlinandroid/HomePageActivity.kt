package com.itscoderslife.hellokotlinandroid

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.itscoderslife.hellokotlinandroid.R.id.welcome2
import kotlinx.android.synthetic.main.activity_home_page.*

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


        goBack.setOnClickListener {
            var retIntent = this.intent
            retIntent.putExtra("return value", "Bye Bye")
            setResult(Activity.RESULT_OK, retIntent)
            finish()
        }

    }
}
