package com.miu.cs473.foodiepal.models

data class Recipe(
    val recipeName: String,
    val ingredients: String,
    val instructions: String,
    val cookingTime: String,
    val userRating: Float,
    val imageResourceId: Int
)