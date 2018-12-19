package com.itscoderslife.hellokotlinandroid.activity.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.itscoderslife.hellokotlinandroid.R
import kotlinx.android.synthetic.main.activity_create_account.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.FirebaseDatabase.*
import com.google.firebase.database.DatabaseReference



class CreateAccountActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mUserDB: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        mAuth = FirebaseAuth.getInstance()
        createAccountPageButton.setOnClickListener {
            if(!createAccDisplayNameText.text.toString().isEmpty() && !createAccEmailText.text.toString().isEmpty() && !createAccPasswordText.text.toString().isEmpty()) {
                this.createAccount(createAccEmailText.text.toString().trim(), createAccPasswordText.text.toString().trim(), createAccDisplayNameText.text.toString().trim())
            } else {
                Toast.makeText(applicationContext, "Please fill in valid details!", Toast.LENGTH_LONG).show()
            }
        }
    }


    fun createAccount(email: String, password: String, displayName: String) {
        Toast.makeText(this, "We have all valid details!", Toast.LENGTH_LONG).show()

        mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task: Task<AuthResult> ->

                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val userID = this.mAuth!!.currentUser!!.uid

                        mUserDB = FirebaseDatabase.getInstance().reference.child("Users").child(userID)

                        var user = HashMap<String, String>()
                        user.put("display_name", displayName)
                        user.put("status", "I am available...")
                        user.put("image", "default")
                        user.put("thumb_image", "default")

                        mUserDB!!.setValue(user).addOnCompleteListener {
                            task: Task<Void> ->
                                if(task.isSuccessful) {
                                    Toast.makeText(this, "User \"$displayName\" is successfully created!", Toast.LENGTH_LONG).show()

                                    val intent = Intent(this, DashboardActivity::class.java)
                                    intent.putExtra("user", userID)
                                    startActivity(intent)
                                    finish()

                                } else {
                                    Log.d("User creation failed : ", task.result.toString())
                                    Log.d("User creation failed : ", task.exception.toString())
                                    Toast.makeText(this, "User \"$displayName\" is NOT created!", Toast.LENGTH_LONG).show()
                                }
                        }

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "User \"$displayName\" is not created!", Toast.LENGTH_LONG).show()
                    }
                }

    }
}
