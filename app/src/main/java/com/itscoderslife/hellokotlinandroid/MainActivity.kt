package com.itscoderslife.hellokotlinandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculatebutton.setOnClickListener {

            if(weightOnEarth.text.isEmpty()) {
                Toast.makeText(applicationContext, "Enter a valid weight in number", Toast.LENGTH_LONG).show()
            } else {
                val weight: Double = weightOnEarth.text.toString().toDouble()
                val marsWt = (calculateWeightOnMars(weight)).toString()
                resultview.text = "Your weight on mars is " + marsWt
            }
        }
    }

    private fun calculateWeightOnMars(weight: Double) : Double {
        val marsGravity = 0.38
        return weight * marsGravity
    }
}
