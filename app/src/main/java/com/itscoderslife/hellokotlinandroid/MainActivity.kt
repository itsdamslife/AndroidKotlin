package com.itscoderslife.hellokotlinandroid

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.itscoderslife.hellokotlinandroid.data.ChoreDataHandler
import com.itscoderslife.hellokotlinandroid.model.Chore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var choreDBHandler: ChoreDataHandler? = null

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        choreDBHandler = ChoreDataHandler(this)

        var chore = Chore()
        chore.choreTitle = "Dams learnt SQL insert and upgrade"
        chore.assignedBy = "Me"
        chore.assignedTo = "Myself"

        val choreId = choreDBHandler!!.createChore(chore)

        // fetch chore
        var chores: Chore? = choreDBHandler!!.fetchChore(1)
        if(chores != null) {
            Log.d("Chore $choreId  fetched Item: ", chores!!.choreTitle)
        }

        // delete a chore
        val del = choreDBHandler!!.deleteChore(choreId.toInt())
        Log.d("chore deleted","Chore $choreId deleted: " + del.toString())

        var ch: Chore? = choreDBHandler!!.fetchChore(1)
        if (ch == null) {
            Log.d("chore not found", "Chore with Id: $choreId not found")
        } else {
            Log.d("Chore fetched", "Chore $choreId fetched: " + ch?.choreTitle)
        }
    }
}
