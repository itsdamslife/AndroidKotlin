package com.itscoderslife.hellokotlinandroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.itscoderslife.hellokotlinandroid.R
import com.itscoderslife.hellokotlinandroid.data.ChoreDataHandler
import com.itscoderslife.hellokotlinandroid.data.ChoreListAdapter
import com.itscoderslife.hellokotlinandroid.model.Chore
import kotlinx.android.synthetic.main.activity_chores_list.*

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
        dialogBuilder = AlertDialog.Builder(this).setView(view)
        dialog = dialogBuilder?.create()
        dialog?.show()
    }

}
