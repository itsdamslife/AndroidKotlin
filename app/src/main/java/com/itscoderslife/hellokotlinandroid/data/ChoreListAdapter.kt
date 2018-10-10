package com.itscoderslife.hellokotlinandroid.data

import android.view.ViewGroup
import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.itscoderslife.hellokotlinandroid.R
import com.itscoderslife.hellokotlinandroid.model.Chore
import kotlinx.android.synthetic.main.popup_add_chore.view.*

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
                    editChore(chore)
                }
            }
        }

        fun deleteChore(id: Int) {
            if(id == null) { return }
            var db: ChoreDataHandler = ChoreDataHandler(context)
            db.deleteChore(id)
        }

        fun editChore(chore: Chore) {

            var dialogBuilder: AlertDialog.Builder? = null
            var dialog: AlertDialog? = null
            var choreDBHandler: ChoreDataHandler = ChoreDataHandler(context)

            val view = LayoutInflater.from(context).inflate(R.layout.popup_add_chore, null)

            var choreName = view.pchoretitleid
            var choreAssignedBy = view.passignedbyid
            var choreAssignedTo = view.passignedtoid
            var saveBtn = view.psaveId


            dialogBuilder = AlertDialog.Builder(context).setView(view)
            dialog = dialogBuilder?.create()
            val show = dialog?.show()


            saveBtn.setOnClickListener {
                if ( !TextUtils.isEmpty(choreName.text.toString().trim()) &&
                        !TextUtils.isEmpty(choreAssignedBy.text.toString()) &&
                        !TextUtils.isEmpty(choreAssignedTo.text.toString())) {

                    chore.choreTitle = choreName.text.toString().trim()
                    chore.assignedBy = choreAssignedBy.text.toString()
                    chore.assignedTo = choreAssignedTo.text.toString()

                    val result = choreDBHandler!!.updateChore(chore)
                    Toast.makeText(context, "Chore updated successfully", Toast.LENGTH_LONG).show()

                    dialog?.dismiss()

                    notifyItemChanged(adapterPosition, chore)

                } else {
                    Toast.makeText(context, "Enter all details, pls…", Toast.LENGTH_LONG).show()
                }

            }
        }

        
    }
}