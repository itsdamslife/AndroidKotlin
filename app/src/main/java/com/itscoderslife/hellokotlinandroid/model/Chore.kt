package com.itscoderslife.hellokotlinandroid.model

import java.sql.Time

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
        this.choreId = id // "TO BE GENERATED - " // TODO: Generate unique Chore Id in this constructor
    }
}