package com.itscoderslife.hellokotlinandroid

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val prefName = "MY_PREFS"
    var myPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveId.setOnClickListener {
            myPref = getSharedPreferences(prefName, 0)
            var editor: SharedPreferences.Editor = myPref!!.edit()

            if (!TextUtils.isEmpty(assignedbyid.text.toString())) {
                var msg = assignedbyid.text.toString()
                editor.putString("name", msg)
                editor.commit()
                textViewId.text = "Welcome $msg"
            } else {
                Toast.makeText(this, "Enter a valid name", Toast.LENGTH_SHORT).show()
            }
        }

        // fetch data back on launch
        var backUp: SharedPreferences = getSharedPreferences(prefName, 0)
        if (backUp.contains("name")) {
            var msg = backUp.getString("name", "")
            textViewId.text = "Welcome back $msg"
        } else {
            Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show()
        }

    }

}
