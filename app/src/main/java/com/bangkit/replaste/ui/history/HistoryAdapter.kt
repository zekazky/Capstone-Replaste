package com.bangkit.replaste.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.replaste.databinding.ItemHistoryBinding
import com.bumptech.glide.Glide

class HistoryAdapter(private val historyList: List<HistoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = historyList[position]
        holder.binding.apply {
            tvDate.text = history.date
            tvTitle.text = history.title
            tvDescription.text = history.description
            // Load image using Glide
            Glide.with(holder.itemView.context)
                .load(history.imageUrl)
                .into(ivPlastic)
        }
    }

    override fun getItemCount() = historyList.size
}
