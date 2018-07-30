package com.itscoderslife.hellokotlinandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonId.setOnClickListener {
            if(editText.text.isEmpty()) {
                Toast.makeText(applicationContext, "Enter your name first…", Toast.LENGTH_SHORT).show()
            } else {
                textView.text = "Welcome " + editText.text + "!!"
            }
        }

        // `this` here and `applicationContext` are same
        Toast.makeText(applicationContext, "Hello user!!", Toast.LENGTH_SHORT).show()
    }
}
