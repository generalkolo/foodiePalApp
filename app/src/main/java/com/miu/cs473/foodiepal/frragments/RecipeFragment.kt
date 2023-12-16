package com.miu.cs473.foodiepal.frragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miu.cs473.foodiepal.R
import com.miu.cs473.foodiepal.adapter.RecipesAdapter
import com.miu.cs473.foodiepal.models.Recipe

class RecipeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    private val recipes: MutableList<Recipe> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_receipe, container, false)

        // Initialize UI components
        recyclerView = view.findViewById(R.id.recipesRecyclerView)
        addButton = view.findViewById(R.id.addRecipeButton)

        // Set up RecyclerView
        val adapter = RecipesAdapter(recipes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Set up FloatingActionButton click listener
        addButton.setOnClickListener {
            showAddRecipeDialog()
        }

        return view
    }

    private fun showAddRecipeDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_recipe, null)

        val recipeNameEditText = dialogView.findViewById<EditText>(R.id.recipeNameEditText)
        val ingredientsEditText = dialogView.findViewById<EditText>(R.id.ingredientsEditText)
        val instructionsEditText = dialogView.findViewById<EditText>(R.id.instructionsEditText)
        val cookingTimeEditText = dialogView.findViewById<EditText>(R.id.cookingTimeEditText)
        val userRatingBar = dialogView.findViewById<RatingBar>(R.id.userRatingBar)

        // Build the dialog
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add Recipe")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                // Get user input from the dialog
                val recipeName = recipeNameEditText.text.toString()
                val ingredients = ingredientsEditText.text.toString()
                val instructions = instructionsEditText.text.toString()
                val cookingTime = cookingTimeEditText.text.toString()
                val userRating = userRatingBar.rating
                val imageResourceId = R.drawable.ic_cookbook

                // Add the new recipe to the list
                recipes.add(
                    Recipe(
                        recipeName,
                        ingredients,
                        instructions,
                        cookingTime,
                        userRating,
                        imageResourceId
                    )
                )

                // Notify the adapter that the data set has changed
                recyclerView.adapter?.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

}