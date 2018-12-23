package com.itscoderslife.hellokotlinandroid.activities

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)

        if(item != null) {
            when (item.itemId) {
                R.id.logoutMenuItem -> {
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                R.id.settingsMenuItem -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                }
            }
        }

        return true
    }

}
