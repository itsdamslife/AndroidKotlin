package com.itscoderslife.hellokotlinandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.itscoderslife.hellokotlinandroid.adapter.PersonListAdapter
import com.itscoderslife.hellokotlinandroid.model.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var personList: ArrayList<Person>? = null
    private var personListAdapter: PersonListAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        personList = ArrayList<Person>()
        layoutManager = LinearLayoutManager(this)
        personListAdapter = PersonListAdapter(personList!!, this)

        personlist.layoutManager = layoutManager
        personlist.adapter = personListAdapter

        for (i in 0..2200) {
            val person = Person()
            person.name = "Coder " + i
            person.age = i + 22
            personList!!.add(person)
        }

        personListAdapter?.notifyDataSetChanged()
    }

}
