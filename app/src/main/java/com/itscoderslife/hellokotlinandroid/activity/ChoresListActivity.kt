package com.itscoderslife.hellokotlinandroid.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.itscoderslife.hellokotlinandroid.R
import com.itscoderslife.hellokotlinandroid.data.ChoreDataHandler
import com.itscoderslife.hellokotlinandroid.data.ChoreListAdapter
import com.itscoderslife.hellokotlinandroid.model.Chore
import kotlinx.android.synthetic.main.activity_chores_list.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup_add_chore.*
import kotlinx.android.synthetic.main.popup_add_chore.view.*

class ChoresListActivity : AppCompatActivity() {

    var choreListAdapter: ChoreListAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var list: ArrayList<Chore>? = null
    var choreDBHandler: ChoreDataHandler? = null
    var dialogBuilder: AlertDialog.Builder? = null
    var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chores_list)

        choreDBHandler = ChoreDataHandler(this)

        list = choreDBHandler?.readChores()
        list!!.reverse()

        choreListAdapter = ChoreListAdapter(list!!,this)
        layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        choresListId.layoutManager = layoutManager
        choresListId.adapter = choreListAdapter

        choresListId.adapter!!.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.add_chore_menu_btn) {
            Log.d("menu item clicked", "Add menu item clicked")
            showCreateChorePopup()
        }
        return super.onOptionsItemSelected(item)
    }

    fun showCreateChorePopup() {

        val view = layoutInflater.inflate(R.layout.popup_add_chore, null)

        var choreName = view.pchoretitleid
        var choreAssignedBy = view.passignedbyid
        var choreAssignedTo = view.passignedtoid
        var saveBtn = view.psaveId


        dialogBuilder = AlertDialog.Builder(this).setView(view)
        dialog = dialogBuilder?.create()
        val show = dialog?.show()


        saveBtn.setOnClickListener {
            if ( !TextUtils.isEmpty(choreName.text.toString().trim()) &&
                    !TextUtils.isEmpty(choreAssignedBy.text.toString()) &&
                    !TextUtils.isEmpty(choreAssignedTo.text.toString())) {

                var chore = Chore()
                chore.choreTitle = choreName.text.toString().trim()
                chore.assignedBy = choreAssignedBy.text.toString()
                chore.assignedTo = choreAssignedTo.text.toString()

                val result = choreDBHandler!!.createChore(chore)
                Toast.makeText(applicationContext, "Chore saved successfully with Id: $result", Toast.LENGTH_LONG).show()

                dialog?.dismiss()

                val intent = intent
                overridePendingTransition(0, 0)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)

            } else {
                Toast.makeText(applicationContext, "Enter all details, pls…", Toast.LENGTH_LONG).show()
            }

        }

    }

}
