package com.itscoderslife.hellokotlinandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.itscoderslife.hellokotlinandroid.data.ChoreDataHandler
import com.itscoderslife.hellokotlinandroid.model.Chore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var choreDBHandler: ChoreDataHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        choreDBHandler = ChoreDataHandler(this)

        var chore = Chore()
        chore.choreTitle = "Dams learning android"
        chore.assignedBy = "Me"
        chore.assignedTo = "Myself"

        choreDBHandler!!.createChore(chore)

        // fetch chore
        var chores: Chore = choreDBHandler!!.fetchChore(1)

        Log.d("Item: ", chores.choreTitle)

        saveId.setOnClickListener {
        }
    }

}
