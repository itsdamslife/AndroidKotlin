package com.itscoderslife.hellokotlinandroid.activity.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.itscoderslife.hellokotlinandroid.R

import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.itscoderslife.hellokotlinandroid.activity.data.RecipeListAdapter
import com.itscoderslife.hellokotlinandroid.activity.model.Recipe
import kotlinx.android.synthetic.main.activity_recipe_list.*
import org.json.JSONException
import org.json.JSONObject

class RecipeList : AppCompatActivity() {

    var volleyRequest:  RequestQueue? = null

    private var recipesList : ArrayList<Recipe>? = null

    private var recipeListAdapter: RecipeListAdapter? = null

    private var layoutManager: RecyclerView.LayoutManager?  = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        recipesList = ArrayList<Recipe>()

        layoutManager = LinearLayoutManager(this)

        recipeListAdapter = RecipeListAdapter(recipesList!!, this)

        recipelistID.layoutManager = layoutManager
        recipelistID.adapter = recipeListAdapter

        volleyRequest = Volley.newRequestQueue(this)

        // Remove the Hardcoded stuff
        val urlstr = "http://www.recipepuppy.com/api/?i=onions,garlic&q=omelet&p=3"
        getRecipes(urlstr)
    }


    fun getRecipes(url: String) {
        val recipesRequest = JsonObjectRequest(Request.Method.GET, url,
                Response.Listener { 
                    response: JSONObject ->
                    try {
                        val results = response.getJSONArray("results")

                        for (i in 0..results.length()-1) {

                            val recObj = results.getJSONObject(i)

                            Log.d("Recipe == ", recObj.getString("title"))

                            var recipe = Recipe()
                            recipe.recipeTitle = recObj.getString("title")
                            recipe.iconPath = recObj.getString("thumbnail")
                            recipe.recipeLink = recObj.getString("href")
                            recipe.recipeIngredients = recObj.getString("ingredients")

                            recipesList?.add(recipe)
                        }

                        recipeListAdapter?.notifyDataSetChanged()


                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Log.d("Parse catch : ", e.localizedMessage)
                    }
                }, 
                Response.ErrorListener { 
                    error : VolleyError ->
                    try {
                        // TODO: java.io.IOException: Cleartext HTTP traffic to www.recipepuppy.com not permitted
                        Log.d("Volley error : ", error.localizedMessage)
                    } catch (e: JSONException) {
                        // Handling error  of errors mother of all errors :(
                        e.printStackTrace()
                        Log.d("Volley error catch : ", e.localizedMessage)
                    }
                })
        volleyRequest!!.add(recipesRequest)
    }

}
