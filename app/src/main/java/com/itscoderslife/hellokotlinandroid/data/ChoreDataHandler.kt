package com.itscoderslife.hellokotlinandroid.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.itscoderslife.hellokotlinandroid.model.Chore
import java.sql.Time
import java.text.DateFormat
import java.util.*

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

    fun createChore(chore: Chore) : Long {
        val db: SQLiteDatabase = writableDatabase

        var values: ContentValues = ContentValues()
        values.put(CHORE_NAME, chore.choreTitle)
        values.put(CHORE_ASSIGNED_TO, chore.assignedTo)
        values.put(CHORE_ASSIGNED_BY, chore.assignedBy)
        values.put(CHORE_ASSIGNED_TIME, System.currentTimeMillis())

        var insert = db.insert(TABLE_NAME, null, values)


        Log.d("DATA INSERTED", "SUCCESS $insert")

        db.close()

        return insert
    }

    fun updateChore(chore: Chore) {

    }

    fun deleteChore(ID: Int) : Int {
        val db: SQLiteDatabase = writableDatabase
        val delete = db?.delete(TABLE_NAME, CHORE_ID + "=?", arrayOf(ID.toString()))
        Log.d("DATA DELETED", "SUCCESS $delete")
        db.close()
        return delete
    }

    fun fetchChore(choreID: Int) : Chore? {
        val db: SQLiteDatabase = writableDatabase

        var cursor = db?.query(TABLE_NAME,
                arrayOf(CHORE_ID, CHORE_NAME, CHORE_ASSIGNED_TO, CHORE_ASSIGNED_BY, CHORE_ASSIGNED_TIME),
                null,
                null, null, null, null)

        if(cursor.count > 0) {
            return null
        }

        if(cursor != null)
            cursor.moveToFirst()

        var chore = Chore()
        chore.choreTitle = cursor.getString(cursor.getColumnIndex(CHORE_NAME))
        chore.assignedTo = cursor.getString(cursor.getColumnIndex(CHORE_ASSIGNED_TO))
        chore.assignedBy = cursor.getString(cursor.getColumnIndex(CHORE_ASSIGNED_BY))
        cursor.getString(cursor.getColumnIndex(CHORE_ASSIGNED_TIME))
        chore.choreId = cursor.getInt(cursor.getColumnIndex(CHORE_ID))

        val dateFormat: java.text.DateFormat = DateFormat.getDateInstance()
        chore.assignedTime = cursor.getLong(cursor.getColumnIndex(CHORE_ASSIGNED_TIME))

        db.close()

        return chore
    }
}