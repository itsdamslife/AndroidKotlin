package com.itscoderslife.hellokotlinandroid.model

class Chore {
    var choreTitle: String
    var assignedTo: String
    var assignedBy: String

    constructor(choreTitle: String, assignedTo: String, assignedBy: String) {
        this.choreTitle = choreTitle
        this.assignedTo = assignedTo
        this.assignedBy = assignedBy
    }
}