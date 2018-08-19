package com.itscoderslife.hellokotlinandroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.itscoderslife.hellokotlinandroid.R
import com.itscoderslife.hellokotlinandroid.model.Person

class PersonListAdapter(private val list: ArrayList<Person>,
                        private val context: Context) : RecyclerView.Adapter<PersonListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(list[p1])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(person: Person) {
            var name = itemView.findViewById<TextView>(R.id.title)
            var desc = itemView.findViewById<TextView>(R.id.desc)

            name.text = person.name
            desc.text = person.age.toString()

            itemView.setOnClickListener {
                Toast.makeText(context, name.text, Toast.LENGTH_LONG).show()

                // You can start an activity here
                // context.startActivity()

            }
        }
    }


}