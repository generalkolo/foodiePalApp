package com.miu.cs473.foodiepal.frragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miu.cs473.foodiepal.R
import com.miu.cs473.foodiepal.adapter.AboutMeAdapter

class AboutMeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    private val aboutMeDetails: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about_me, container, false)

        // Initialize UI components
        recyclerView = view.findViewById(R.id.aboutMeRecyclerView)
        addButton = view.findViewById(R.id.addDetailButton)

        // Set up RecyclerView
        val adapter = AboutMeAdapter(aboutMeDetails)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Set up FloatingActionButton click listener
        addButton.setOnClickListener {
            showAddDetailDialog(adapter)
        }

        return view
    }

    private fun showAddDetailDialog(adapter: AboutMeAdapter) {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_about_me_detail, null)

        val detailEditText: EditText = dialogView.findViewById(R.id.detailEditText)

        builder.setView(dialogView)
            .setTitle("Add Detail")
            .setPositiveButton("Add") { _, _ ->
                // Get user input from the dialog
                val detail = detailEditText.text.toString()

                // Add a new detail to the list
                aboutMeDetails.add(detail)

                // Update the adapter's dataset
                adapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)

        builder.create().show()
    }

}