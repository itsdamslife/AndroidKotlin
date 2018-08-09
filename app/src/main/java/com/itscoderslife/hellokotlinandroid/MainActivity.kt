package com.itscoderslife.hellokotlinandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext,"On create",Toast.LENGTH_SHORT).show()

        var uname = findViewById<EditText>(R.id.username)
        var pswd = findViewById<EditText>(R.id.password)

        enter.setOnClickListener {
            uname.setTextKeepState("")
            pswd.setTextKeepState("")
            Toast.makeText(applicationContext,"clear tapped",Toast.LENGTH_SHORT).show()
        }


        login.setOnClickListener {
            var intent = Intent(this, HomePageActivity::class.java)
            intent.putExtra("user", uname.text)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            val returnData = data!!.extras.get("return value").toString()
            textView.setTextKeepState("Hope you had a nice trip to second activity!!")
            Toast.makeText(applicationContext,returnData,Toast.LENGTH_LONG).show()
        }


    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Toast.makeText(applicationContext,"On Destroy",Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Toast.makeText(applicationContext,"On start",Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Toast.makeText(applicationContext,"On stop",Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Toast.makeText(applicationContext,"On pause",Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Toast.makeText(applicationContext,"On resume",Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onPostResume() {
//        super.onPostResume()
//        Toast.makeText(applicationContext,"On post resume",Toast.LENGTH_SHORT).show()
//    }
}
