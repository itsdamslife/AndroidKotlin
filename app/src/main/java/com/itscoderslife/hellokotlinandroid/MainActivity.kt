package com.itscoderslife.hellokotlinandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCheckBoxClicked(view: View) {
        view as CheckBox
        val isChecked: Boolean = view.isChecked

        when(view.id) {
            R.id.cbfruits -> if (isChecked) { Log.d("Fruits ", "Checked") } else { Log.d("Fruits ", "Un-Checked") }
            R.id.cbveggies -> if (isChecked) { Log.d("Veggies ", "Checked") } else { Log.d("Veggies ", "Un-Checked") }
            R.id.cbdrinks -> if (isChecked) { Log.d("Drinks ", "Checked") } else { Log.d("Drinks ", "Un-Checked") }
        }
    }
}
