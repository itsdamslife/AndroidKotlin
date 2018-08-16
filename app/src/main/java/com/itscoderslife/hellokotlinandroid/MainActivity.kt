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
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onClick(v: View?) {
        when(v!!.id) {
            lionImgView.id -> {
                val intent = Intent(this, description::class.java)
                intent.putExtra("animal", "lion")
                startActivity(intent)
            }
            cheetahImgView.id -> {
                val intent = Intent(this, description::class.java)
                intent.putExtra("animal", "cheetah")
                startActivity(intent)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        print("Animal bios!!")

        var cheetah = cheetahImgView
        var lion = lionImgView

        lion.setOnClickListener(this)
        cheetah.setOnClickListener(this)

    }

}
