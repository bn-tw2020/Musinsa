package com.homework.myapplication.presentation.adapter.style

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.homework.myapplication.data.model.Style
import com.homework.myapplication.databinding.ItemStyleBinding

class StyleAdapter(
    private val onLinkItem: (String?) -> Unit
) : ListAdapter<Style, StyleAdapter.StyleViewHolder>(StyleDiff()) {

    private lateinit var binding: ItemStyleBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StyleViewHolder {
        binding = ItemStyleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StyleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StyleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StyleViewHolder(
        private val binding: ItemStyleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(style: Style) {
            binding.style = style
            itemView.setOnClickListener { onLinkItem(style.linkURL) }
        }
    }
}

class StyleDiff : DiffUtil.ItemCallback<Style>() {
    override fun areItemsTheSame(oldItem: Style, newItem: Style): Boolean {
        return oldItem.thumbnailURL == newItem.thumbnailURL
    }

    override fun areContentsTheSame(oldItem: Style, newItem: Style): Boolean {
        return oldItem == newItem
    }

}