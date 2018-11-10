package com.itscoderslife.hellokotlinandroid.activity.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.itscoderslife.hellokotlinandroid.R

import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class RecipeList : AppCompatActivity() {

    var volleyRequest:  RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        volleyRequest = Volley.newRequestQueue(this)
    }


    fun getRecipes(url: String) {
        val recipesRequest = JsonObjectRequest(Request.Method.GET, url,
                Response.Listener { 
                    response: JSONObject ->
                    try {
                        // TODO: Parse the results

                    } catch (e: JSONException) {
                        // TODO: Handle parsing exceptions
                    }
                }, 
                Response.ErrorListener { 
                    error : VolleyError ->
                    try {
                        // TODO: Handle Volley error
                    } catch (e: JSONException) {
                        // TODO: Handle Volley error exceptions - not sure why the hell is this
                        // Handling error  of errors mother of all errors :(
                    }
                })

    }

}
