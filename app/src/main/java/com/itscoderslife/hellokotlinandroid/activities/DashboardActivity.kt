package com.itscoderslife.hellokotlinandroid.activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.itscoderslife.hellokotlinandroid.R
import com.itscoderslife.hellokotlinandroid.adapters.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var sectionAdapter: SectionPagerAdapter? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        sectionAdapter = SectionPagerAdapter(supportFragmentManager)
        dashViewPagerId.adapter = sectionAdapter
        mainTabs.setupWithViewPager(dashViewPagerId)

        mainTabs.setTabTextColors(Color.LTGRAY, Color.WHITE)

        if(this.intent.extras != null) {
            val username = intent.extras.get("user")
            Toast.makeText(this, "Welcome $username!", Toast.LENGTH_LONG).show()
        }

    }



}
