package com.itscoderslife.hellokotlinandroid.activity.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.itscoderslife.hellokotlinandroid.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        if(this.intent.extras != null) {
            val username = intent.extras.get("user")
            Toast.makeText(this, "Welcome $username!", Toast.LENGTH_LONG).show()

            dashwelcomtext.text = "Welcome to whatsup $username!"
        }




    }
}
