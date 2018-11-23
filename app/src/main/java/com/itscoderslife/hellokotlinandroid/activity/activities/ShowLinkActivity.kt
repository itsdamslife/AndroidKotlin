package com.itscoderslife.hellokotlinandroid.activity.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.itscoderslife.hellokotlinandroid.R
import kotlinx.android.synthetic.main.activity_show_link.*

class ShowLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_link)

        val extras = intent.extras

        if (extras != null) {
            val link = extras.getString("link").toString()
            webviewID.loadUrl(link)
        }

    }
}
