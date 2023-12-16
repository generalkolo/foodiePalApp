package com.miu.cs473.foodiepal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miu.cs473.foodiepal.R
import com.miu.cs473.foodiepal.models.Recipe

class RecipesAdapter(private val recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeNameTextView: TextView = itemView.findViewById(R.id.recipeNameTextView)
        private val ingredientsTextView: TextView = itemView.findViewById(R.id.ingredientsTextView)
        private val cookingTimeTextView: TextView = itemView.findViewById(R.id.cookingTimeTextView)
        private val userRatingBar: RatingBar = itemView.findViewById(R.id.userRatingBar)
        private val recipeImageView: ImageView = itemView.findViewById(R.id.recipeImageView)

        fun bind(recipe: Recipe) {
            recipeNameTextView.text = recipe.recipeName
            ingredientsTextView.text = recipe.ingredients
            cookingTimeTextView.text = "Cooking Time: ${recipe.cookingTime} mins"
            userRatingBar.rating = recipe.userRating
            recipeImageView.setImageResource(recipe.imageResourceId)
        }
    }
}