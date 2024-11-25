package com.bangkit.replaste.ui.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.replaste.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyAdapter: HistoryAdapter
    private val historyList = mutableListOf<HistoryItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadHistoryData()
    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter(historyList)
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(this@HistoryActivity)
            adapter = historyAdapter
        }
    }

    private fun loadHistoryData() {
        // Contoh data
        historyList.add(
            HistoryItem(
                date = "25 Nov 2024",
                title = "PET Bottle",
                description = "Botol plastik jenis PET",
                imageUrl = "url_gambar"
            )
        )
        historyAdapter.notifyDataSetChanged()
    }
}
