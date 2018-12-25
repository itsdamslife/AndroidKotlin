package com.itscoderslife.hellokotlinandroid.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import com.itscoderslife.hellokotlinandroid.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    var mDB: DatabaseReference? = null
    var mUser: FirebaseUser? = null
    var storage: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        mUser = FirebaseAuth.getInstance().currentUser
        mDB = FirebaseDatabase.getInstance().reference.child("Users").child(mUser!!.uid)
        mDB!!.addValueEventListener (object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val displayName = snapshot!!.child("display_name").value
                val status = snapshot!!.child("status").value
                val image = snapshot!!.child("image").value
                val thumb_image = snapshot!!.child("thumb_image").value

                displayNameId.text = displayName!!.toString()
                statusId.text = status!!.toString()

            }

            override fun onCancelled(snapshot: DatabaseError) {

            }
        })

        changePicBtnId.setOnClickListener {
            this.loadImagePicker()
        }

    }

    fun loadImagePicker() {
//        val intent = Intent(this, CropImageActivity::class.java)
//        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            Toast.makeText(this, "Image fetched from gallery", Toast.LENGTH_LONG).show()
        }
    }


}
