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
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageActivity
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_settings.*
import android.R.attr.data
import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.File


class SettingsActivity : AppCompatActivity() {

    var mDB: DatabaseReference? = null
    var mUser: FirebaseUser? = null
    var mStorage: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        mUser = FirebaseAuth.getInstance().currentUser
        mStorage = FirebaseStorage.getInstance().reference
        mDB = FirebaseDatabase.getInstance().reference.child("Users").child(mUser!!.uid)
        mDB!!.addValueEventListener (object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val displayName = snapshot!!.child("display_name").value
                val status = snapshot!!.child("status").value
                val image = snapshot!!.child("image").value
                val thumb_image = snapshot!!.child("thumb_image").value

                displayNameId.text = displayName!!.toString()
                statusId.text = status!!.toString()

                Log.d("whatsup Image :", "$image")
                if(!image!!.equals("default")) {
                    Log.d("whatsup Image :", "Not default")
                    Picasso.get().load(image.toString()).into(profile_image)
//                    profile_image!!.setImageURI(Uri.parse(image.toString()))
                } else {
                    Log.d("whatsup Image :", "is default")
                }
            }

            override fun onCancelled(snapshot: DatabaseError) {

            }
        })

        changePicBtnId.setOnClickListener {
            this.loadImagePicker()
        }

    }

    fun loadImagePicker() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode === Activity.RESULT_OK) {
                val resultUri = result.uri

                val userId = mUser!!.uid
                var thumb = File(resultUri.path)

                var thumbBitmap = Compressor(this)
                        .setMaxWidth(200)
                        .setMaxHeight(200)
                        .setQuality(65)
                        .compressToBitmap(thumb)

                var byteArray = ByteArrayOutputStream()
                thumbBitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArray)

                var thumbByteArray: ByteArray
                thumbByteArray = byteArray.toByteArray()

                var filePath = mStorage!!.child("chat_profile_images")
                        .child(userId + ".jpg")

                var thumbFilePath = mStorage!!.child("chat_profile_images")
                        .child("thumbs")
                        .child(userId + ".jpg")

                filePath.putFile(resultUri).continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    return@Continuation mStorage!!.child("${task.result!!.metadata!!.path.toString()}").downloadUrl
                }).addOnCompleteListener {
                            task ->
                            if(task.isSuccessful) {
                                var fileURI = task.result

                                Log.d("download URI : ", "is $fileURI")

                                thumbFilePath.putBytes(thumbByteArray).continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                                    if (!task.isSuccessful) {
                                        task.exception?.let {
                                            throw it
                                        }
                                    }
                                    return@Continuation mStorage!!.child("${task.result!!.metadata!!.path.toString()}").downloadUrl
                                }).addOnCompleteListener {
                                    thumbTask ->
                                    if(thumbTask.isSuccessful) {
                                        var thumbFileURI = thumbTask.result.toString()

                                        Log.d("whatsup thumb download URI : ", "is $thumbFileURI")

                                        var updateObj = HashMap<String, Any>()
                                        updateObj.put("image", fileURI.toString())
                                        updateObj.put("thumb_image", thumbFileURI)

                                        mDB!!.updateChildren(updateObj).addOnCompleteListener {
                                            updateTask: Task<Void> ->
                                            if(updateTask.isSuccessful) {
                                                Toast.makeText(this, "Updated profile Pic successfully!!", Toast.LENGTH_SHORT).show()
                                            } else {
                                                Toast.makeText(this, "Updated profile Pic failed!!", Toast.LENGTH_LONG).show()
                                            }
                                        }

                                    } else {
                                        Log.d("whatsup thumb download URI : ", "could not donwload!!")
                                    }
                                }

                            } else {
                                Log.d("whatsup download URI : ", "could not donwload!!")
                            }
                        }

                Toast.makeText(this, "Image fetched from gallery : $resultUri", Toast.LENGTH_LONG).show()
            } else if (resultCode === CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }


}
