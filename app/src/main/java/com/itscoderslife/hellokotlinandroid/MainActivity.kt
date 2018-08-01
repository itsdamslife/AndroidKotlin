package com.itscoderslife.hellokotlinandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.CheckBox
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity(), OnClickListener {

    private val marsGravity: Double = 0.38
    private val venusGravity: Double = 0.91
    private val jupiterGravity: Double = 2.34

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculatebutton.setOnClickListener {

            if(weightOnEarth.text.isEmpty()) {
                Toast.makeText(applicationContext, "Enter a valid weight in number", Toast.LENGTH_LONG).show()
            } else {
                val weight: Double = weightOnEarth.text.toString().toDouble()
                calculateWeight(weight, null)
            }
        }

        cbmars.setOnClickListener(this)
        cbvenus.setOnClickListener(this)
        cbjupiter.setOnClickListener(this)

    }

    private fun calculateWeight(weight: Double, cb: CheckBox?) {
        var result: Double = weight
        val isChecked: Boolean = cb?.isChecked ?: false

        when(cb?.id) {
            R.id.cbmars -> if (isChecked) {
                result = weight * marsGravity
            }
            R.id.cbvenus -> if (isChecked) {
                result = weight * venusGravity
            }
            R.id.cbjupiter -> if (isChecked) {
                result = weight * jupiterGravity
            }
        }

        resultview.text = "Your weight is ".toString() + result.toString()
    }

    override fun onClick(view: View) {

        if(weightOnEarth.text.isEmpty()) {
            Toast.makeText(applicationContext, "Enter a valid weight in number", Toast.LENGTH_LONG).show()
            return
        }

        val weight: Double = weightOnEarth.text.toString().toDouble()

        view as CheckBox
        calculateWeight(weight, view)
    }
}
