package com.itscoderslife.hellokotlinandroid.activity.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.itscoderslife.hellokotlinandroid.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        myRef.setValue("Hi from My Chat App!!")
    }
}
