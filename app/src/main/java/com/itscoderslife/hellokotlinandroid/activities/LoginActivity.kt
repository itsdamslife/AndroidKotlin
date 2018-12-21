package com.itscoderslife.hellokotlinandroid.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.itscoderslife.hellokotlinandroid.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mUserDB: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        loginPageButton.setOnClickListener {
            if(!loginEmailText.text.toString().isEmpty() && !loginPasswordText.text.toString().isEmpty()) {
                this.login(loginEmailText.text.toString().trim(), loginPasswordText.text.toString().trim())
            } else {
                Toast.makeText(applicationContext, "Please fill in valid details!", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun login(email: String, password: String) {
        Toast.makeText(this, "Entries are valid!", Toast.LENGTH_LONG).show()

        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            task: Task<AuthResult> ->

            if (task.isSuccessful) {
                Toast.makeText(this, "${email.split("@")[0]} logged in successfully!", Toast.LENGTH_LONG).show()

                val userID = this.mAuth!!.currentUser!!.uid
                mUserDB = FirebaseDatabase.getInstance().reference.child("Users").child(userID)

                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("user", email.split("@")[0])
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Login for ${email.split("@")[0]} failed!", Toast.LENGTH_LONG).show()
            }

        }
    }

}

