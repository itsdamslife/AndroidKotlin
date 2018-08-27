package com.itscoderslife.hellokotlinandroid.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ChoreDataHandler(context: Context):
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement = "CREATE TABLE " + TABLE_NAME + "(" + CHORE_ID + " INTEGER PRIMARY KEY, " +
                                    CHORE_NAME + " TEXT, " +
                                    CHORE_ASSIGNED_TO + " TEXT, " +
                                    CHORE_ASSIGNED_BY + " TEXT, " +
                                    CHORE_ASSIGNED_TIME + " LONG)"

        db?.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase?, v1: Int, v2: Int) {
        db?.execSQL("DROP TABLE " + TABLE_NAME)
        onCreate(db)
    }

    /* CRUD ops */

}