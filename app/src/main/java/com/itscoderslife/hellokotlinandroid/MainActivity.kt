package com.itscoderslife.hellokotlinandroid

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var catImageView = findViewById<ImageView>(R.id.catwoman)
        catImageView.setColorFilter(Color.DKGRAY)

        catImageView.setOnClickListener {
            catImageView.clearColorFilter()
            catImageView.setBackgroundColor(Color.DKGRAY)
        }

        val colorsArray = arrayOf(Color.BLACK, Color.DKGRAY, Color.MAGENTA, Color.LTGRAY, Color.TRANSPARENT)
        var dogImgView = findViewById<ImageView>(R.id.doggie)
        dogImgView.setOnClickListener {
            val index = Random().nextInt(colorsArray.count())
            dogImgView.setColorFilter(colorsArray[index], PorterDuff.Mode.OVERLAY)
        }

        var tapMeBtn = findViewById<Button>(R.id.tapMe)
        tapMeBtn.setOnClickListener {
            val index = Random().nextInt(colorsArray.count())
            mainView.setBackgroundColor(colorsArray[index])
        }


    }
}
