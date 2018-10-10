package com.itscoderslife.hellokotlinandroid.data

import android.view.ViewGroup
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.itscoderslife.hellokotlinandroid.R
import com.itscoderslife.hellokotlinandroid.model.Chore

class ChoreListAdapter(private var list: ArrayList<Chore>, private var context: Context)
    : RecyclerView.Adapter<ChoreListAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindViews(list[p1])
    }

    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, holder, false)
        return ViewHolder(view, context, list)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    inner class ViewHolder(itemView: View, context: Context, list: ArrayList<Chore>) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var choreName = itemView.findViewById(R.id.listChoreTitle) as TextView
        var choreAssignedTo = itemView.findViewById(R.id.listChoreAssignedto) as TextView
        var choreAssignedBy = itemView.findViewById(R.id.listChoreAssignedby) as TextView
        var choreDate = itemView.findViewById(R.id.listChoreDate) as TextView
        var deleteBtn = itemView.findViewById(R.id.deleteId) as Button
        var editBtn = itemView.findViewById(R.id.editId) as Button

        fun bindViews(chore: Chore) {
            choreName.text = chore.choreTitle
            choreAssignedBy.text = chore.assignedBy
            choreAssignedTo.text = chore.assignedTo
            choreDate.text = chore.showHumanDate(chore.assignedTime!!)
            deleteBtn.setOnClickListener(this)
            editBtn.setOnClickListener(this)
        }

        override fun onClick(view: View?) {

            var pos = adapterPosition
            var chore = list[pos]

            when(view!!.id) {
                deleteBtn.id -> {
                    Toast.makeText(context, "Delete button clicked", Toast.LENGTH_SHORT).show()
                    deleteChore(chore.choreId!!)
                    list.remove(chore)
                    notifyItemRemoved(pos)
                }

                editBtn.id -> {
                    Toast.makeText(context, "Edit button clicked", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun deleteChore(id: Int) {
            if(id == null) { return }
            var db: ChoreDataHandler = ChoreDataHandler(context)
            db.deleteChore(id)
        }
        
    }
}