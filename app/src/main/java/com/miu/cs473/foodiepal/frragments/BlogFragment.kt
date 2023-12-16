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
import com.miu.cs473.foodiepal.adapter.BlogAdapter
import com.miu.cs473.foodiepal.models.BlogPost

class BlogFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton

    private val blogPosts: MutableList<BlogPost> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blog, container, false)

        // Initialize UI components
        recyclerView = view.findViewById(R.id.blogRecyclerView)
        addButton = view.findViewById(R.id.addBlogButton)

        // Set up RecyclerView
        val adapter = BlogAdapter(blogPosts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Set up FloatingActionButton click listener
        addButton.setOnClickListener {
            // Add a new blog post to the list (for demonstration purposes)
            showAddBlogDialog(adapter)
        }

        return view
    }

    private fun showAddBlogDialog(adapter: BlogAdapter) {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_blog, null)

        val blogTitleEditText: EditText = dialogView.findViewById(R.id.blogTitleEditText)
        val blogContentEditText: EditText = dialogView.findViewById(R.id.blogContentEditText)

        builder.setView(dialogView)
            .setTitle("Add Blog Post")
            .setPositiveButton("Add") { _, _ ->
                // Get user input from the dialog
                val blogTitle = blogTitleEditText.text.toString()
                val blogContent = blogContentEditText.text.toString()

                // Create a new BlogPost and add it to the list
                val newBlogPost = BlogPost(blogTitle, blogContent, "Demo User")
                blogPosts.add(newBlogPost)

                // Notify the adapter that the data set has changed
                adapter?.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)

        builder.create().show()
    }
}