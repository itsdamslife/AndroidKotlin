package com.itscoderslife.hellokotlinandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_description.*

class description : AppCompatActivity() {

    var cheetahInfo = "Cheetah Lorem ipsum"
    var lionInfo = "Lion Lorem ipsum"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        var animal = intent.extras
        if (animal != null) {
            when(animal.get("animal")) {
                "cheetah" -> {
                    animalid.setImageDrawable(application.getDrawable(R.mipmap.cheetah))
                    descid.setTextKeepState(cheetahInfo)
                }
                "lion" -> {
                    animalid.setImageDrawable(application.getDrawable(R.mipmap.lion))
                    descid.setTextKeepState(lionInfo)
                }
            }
        }

    }
}
