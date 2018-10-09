package com.itscoderslife.hellokotlinandroid.activity

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.itscoderslife.hellokotlinandroid.R
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

        checkDB()

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

                val intent = Intent(this,ChoresListActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Enter all details, pls…", Toast.LENGTH_LONG).show()
            }
            progressDialog!!.cancel()
        }
    }

    fun checkDB() {
        if(choreDBHandler!!.getChoresCount() > 0) {
            startActivity(Intent(this, ChoresListActivity::class.java))
        }
    }
}
