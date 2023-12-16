package com.miu.cs473.foodiepal.frragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miu.cs473.foodiepal.R
import com.miu.cs473.foodiepal.adapter.MealPlannerAdapter
import com.miu.cs473.foodiepal.models.MealPlan

class MealPlannerFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    private val mealPlans: MutableList<MealPlan> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal_planner, container, false)

        // Initialize UI components
        recyclerView = view.findViewById(R.id.mealPlannerRecyclerView)
        addButton = view.findViewById(R.id.addMealButton)

        // Set up RecyclerView
        val adapter = MealPlannerAdapter(mealPlans)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Set up FloatingActionButton click listener
        addButton.setOnClickListener {
            showAddMealDialog()
        }

        return view
    }

    private fun showAddMealDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_meal, null)

        val daySpinner: Spinner = dialogView.findViewById(R.id.daySpinner)
        val mealTimeSpinner: Spinner = dialogView.findViewById(R.id.mealTimeSpinner)
        val mealNameEditText: EditText = dialogView.findViewById(R.id.mealNameEditText)

        // Set up Spinners
        val days = resources.getStringArray(R.array.days_array)
        val mealTimes = resources.getStringArray(R.array.meal_times_array)

        val dayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, days)
        val mealTimeAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, mealTimes)

        daySpinner.adapter = dayAdapter
        mealTimeSpinner.adapter = mealTimeAdapter

        // Build the dialog
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add Meal to Planner")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                // Get user input from the dialog
                val selectedDay = daySpinner.selectedItem.toString()
                val selectedMealTime = mealTimeSpinner.selectedItem.toString()
                val mealName = mealNameEditText.text.toString()

                // Add the new meal plan to the list
                mealPlans.add(MealPlan(selectedDay, selectedMealTime, mealName))

                // Notify the adapter that the data set has changed
                recyclerView.adapter?.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

}