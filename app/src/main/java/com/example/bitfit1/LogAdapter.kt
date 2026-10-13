package com.example.bitfit1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LogAdapter(private val logs: List<LogItem>) : RecyclerView.Adapter<LogAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView
        val hoursTextView: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            dateTextView = itemView.findViewById(R.id.logDate)
            hoursTextView = itemView.findViewById(R.id.logHours)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val logView = inflater.inflate(R.layout.item_log, parent, false)
        // Return a new holder instance
        return ViewHolder(logView)
    }
    // Populate data into the item through the holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val log = logs.get(position)
        // Set item views based on views and data model
        holder.dateTextView.text = log.date
        holder.hoursTextView.text = log.hours
    }
    override fun getItemCount(): Int {
        return logs.size
    }
}