package com.itscoderslife.hellokotlinandroid.model

import java.sql.Time

class Chore {
    var choreId: String
    var choreTitle: String
    var assignedTo: String
    var assignedBy: String
    var assignedTime: Time

    constructor(choreTitle: String, assignedTo: String, assignedBy: String, assignedTime: Time) {
        this.choreTitle = choreTitle
        this.assignedTo = assignedTo
        this.assignedBy = assignedBy
        this.assignedTime = assignedTime
        this.choreId = "TO BE GENERATED - " // TODO: Generate unique Chore Id in this constructor
    }
}