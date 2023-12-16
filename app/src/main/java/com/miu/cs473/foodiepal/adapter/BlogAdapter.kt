package com.miu.cs473.foodiepal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miu.cs473.foodiepal.R
import com.miu.cs473.foodiepal.models.BlogPost

class BlogAdapter(private val blogPosts: MutableList<BlogPost>) :
    RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_blog_post, parent, false)
        return BlogViewHolder(view)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blogPost = blogPosts[position]
        holder.bind(blogPost)
    }

    override fun getItemCount(): Int {
        return blogPosts.size
    }

    inner class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        private val authorTextView: TextView = itemView.findViewById(R.id.authorTextView)

        fun bind(blogPost: BlogPost) {
            titleTextView.text = blogPost.title
            contentTextView.text = blogPost.content
            authorTextView.text = "Author: ${blogPost.author}"
        }
    }
}