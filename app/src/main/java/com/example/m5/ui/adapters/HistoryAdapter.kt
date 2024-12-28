package com.example.m5.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.m5.data.local.HistoryEntity
import com.example.m5.databinding.ItemHistoryBinding
import com.example.m5.ui.fragments.History

class HistoryAdapter(private var history: List<HistoryEntity>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(historyEntity: HistoryEntity){
            binding.apply {
                tvFirstName.text = historyEntity.firstName
                tvSecondName.text = historyEntity.secondName
                result.text = historyEntity.result
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context))
        return HistoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return history.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.onBind(history[position])
    }
}