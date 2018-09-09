package com.itscoderslife.hellokotlinandroid

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.itscoderslife.hellokotlinandroid.data.ChoreDataHandler
import com.itscoderslife.hellokotlinandroid.model.Chore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var choreDBHandler: ChoreDataHandler? = null
    var progressDialog: ProgressDialog? = null

    fun saveToDatabase(chore: Chore) : Long {
        return choreDBHandler!!.createChore(chore)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        choreDBHandler = ChoreDataHandler(this)
        progressDialog = ProgressDialog(this)

        saveId.setOnClickListener {
//            ProgressDialog.show(this, "Chore App", "Saving...", false, true) {
//                print("Progress Dialog cancelled!!")
//            }

            progressDialog!!.setMessage("Saving...")
            progressDialog!!.show()

            if ( !TextUtils.isEmpty(choretitleid.text.toString()) &&
                    !TextUtils.isEmpty(assignedtoid.text.toString()) &&
                    !TextUtils.isEmpty(assignedbyid.text.toString())) {

                var chore = Chore()
                chore.choreTitle = choretitleid.text.toString()
                chore.assignedBy = assignedbyid.text.toString()
                chore.assignedTo = assignedtoid.text.toString()

                val result = saveToDatabase(chore)
                Toast.makeText(applicationContext, "Chore saved successfully with Id: $result", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Enter all details, pls…", Toast.LENGTH_LONG).show()
            }
            progressDialog!!.cancel()
        }


        /*



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

        */
    }
}
