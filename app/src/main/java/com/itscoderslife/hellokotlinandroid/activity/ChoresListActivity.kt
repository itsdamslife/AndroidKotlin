package com.itscoderslife.hellokotlinandroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
}
