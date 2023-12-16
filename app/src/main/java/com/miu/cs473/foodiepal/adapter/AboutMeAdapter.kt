package com.miu.cs473.foodiepal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miu.cs473.foodiepal.R

class AboutMeAdapter(private val aboutMeDetails: List<String>) :
    RecyclerView.Adapter<AboutMeAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_about_me_detail, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val detail = aboutMeDetails[position]
        holder.bind(detail)
    }

    override fun getItemCount(): Int {
        return aboutMeDetails.size
    }

    inner class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val detailTextView: TextView = itemView.findViewById(R.id.detailTextView)

        fun bind(detail: String) {
            detailTextView.text = detail
        }
    }
}