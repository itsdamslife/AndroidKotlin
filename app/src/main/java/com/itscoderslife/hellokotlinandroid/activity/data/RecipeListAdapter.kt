package com.itscoderslife.hellokotlinandroid.activity.data

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.itscoderslife.hellokotlinandroid.R
import com.itscoderslife.hellokotlinandroid.activity.activities.ShowLinkActivity
import com.itscoderslife.hellokotlinandroid.activity.model.Recipe
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class RecipeListAdapter(private val list: ArrayList<Recipe>, private val context: Context): RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }


    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

        var titleView: TextView? = itemView.findViewById<TextView>(R.id.recipeTitle)
        var ingredients: TextView? = itemView.findViewById<TextView>(R.id.ingredientsView)
        var iconView: ImageView? = itemView.findViewById<ImageView>(R.id.imageView)
        var button: Button? = itemView.findViewById<Button>(R.id.OpenInBrowser)

        fun bindView(recipe: Recipe) {
            titleView?.text = recipe.recipeTitle
            ingredients?.text = recipe.recipeIngredients
            button?.setOnClickListener {
                var intent = Intent(context, ShowLinkActivity::class.java)
                intent.putExtra("link", recipe.recipeLink.toString())
                context.startActivity(intent)
            }

            if(!TextUtils.isEmpty(recipe.iconPath)) {
                Picasso.get()
                        .load(recipe.iconPath)
                        .placeholder(android.R.drawable.ic_menu_report_image)
                        .error(android.R.drawable.ic_menu_report_image)
                        .into(iconView)
            } else {
                Picasso.get()
                        .load(R.mipmap.ic_launcher)
                        .placeholder(android.R.drawable.ic_menu_report_image)
                        .error(android.R.drawable.ic_menu_report_image)
                        .into(iconView)
            }
        }
    }

}