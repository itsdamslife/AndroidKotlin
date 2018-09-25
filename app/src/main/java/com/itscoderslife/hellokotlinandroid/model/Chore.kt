package com.itscoderslife.hellokotlinandroid.model

import java.sql.Date
import java.sql.Time
import java.text.DateFormat

class Chore() {
    var choreId: Int? = null
    var choreTitle: String? = null
    var assignedTo: String? = null
    var assignedBy: String? = null
    var assignedTime: Long? = null

    constructor(choreTitle: String, assignedBy: String, assignedTo: String, assignedTime: Long,  id: Int): this() {
        this.choreTitle = choreTitle
        this.assignedTo = assignedTo
        this.assignedBy = assignedBy
        this.assignedTime = assignedTime
        this.choreId = id
    }

    fun showHumanDate(timeAssigned: Long): String {

        var dateFormat: java.text.DateFormat = DateFormat.getDateInstance()
        var formattedDate: String = dateFormat.format(Date(timeAssigned).time)

        return "Created: ${formattedDate}"

    }

    override fun toString(): String {
        return "Chore(choreName=$choreTitle, assignedBy=$assignedBy, assignedTo=$assignedTo, timeAssigned=$assignedTime, id=$choreId)"
    }

}