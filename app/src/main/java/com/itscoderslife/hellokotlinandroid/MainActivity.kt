package com.itscoderslife.hellokotlinandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // List view < Adapters < Data source < Array of any type

        val dataSrc = arrayOf("rang","shash","vish","chitr","jag","sav","gur","sud","rav","rash","ram","rag","dam","dee","vas","nam")

        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataSrc)

        listviewid.adapter = adapter

        listviewid.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, listviewid.getItemAtPosition(i).toString(), Toast.LENGTH_LONG).show()
        }

    }

}
