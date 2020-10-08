package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.entities.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]

        holder.textView.text = item.sleepQuality.toString()

        val textColor = if (item.sleepQuality <= 1) {
            Color.RED
        } else {
            Color.BLACK
        }

        holder.textView.setTextColor(textColor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val textView = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(textView)
    }
}