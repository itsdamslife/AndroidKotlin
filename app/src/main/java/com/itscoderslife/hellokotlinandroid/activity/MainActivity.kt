package com.itscoderslife.hellokotlinandroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.itscoderslife.hellokotlinandroid.R
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    var requestQueue: RequestQueue? = null
    val reqString = "https://www.magadistudio.com/" + "complete-android-developer-course-source-files/string.html"

    val newReqString = "https://jsonplaceholder.typicode.com/todos"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestQueue = Volley.newRequestQueue(this)

//        getString(reqString)
        getJSONArray(newReqString)

    }

    fun getJSONArray(url: String) {
        val jsonArrayReq = JsonArrayRequest(Request.Method.GET, url,
                Response.Listener {
                    response: JSONArray ->
                    try {
                        Log.d("JSON Response ====> ", response.toString())

                        for (i in 0 until response.length()) {
                            val todo = response.getJSONObject(i)
                            val todoTitle = todo.getString("title")
                            val user = todo.getString("userId")
                            Log.d("Todo title # ${user} : ", todoTitle)
                        }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener {
                    error: VolleyError? ->
                    try {
                        Log.d("Volley Request error", error.toString())
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                })
        requestQueue?.add(jsonArrayReq)
    }

    fun getString(url: String) {

        val stringReq = StringRequest(Request.Method.GET, url,
                Response.Listener {
                    try {
                        Log.d("Volley Response", "Response : $it")
                    } catch (e: Exception) {
                        Log.d("Volley Response error", "Request error ${e.localizedMessage}")
                    }
                },
                Response.ErrorListener {
                    Log.d("Volley Request error", "Request error : ${it.localizedMessage}")
                })

        requestQueue?.add(stringReq)
    }


}
