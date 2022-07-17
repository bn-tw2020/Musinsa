package com.homework.myapplication.presentation.adapter.scroll_grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.homework.myapplication.data.model.Good
import com.homework.myapplication.databinding.ItemScrollGridBinding

class GoodAdapter(
    private val onLinkItem: (String?) -> Unit
) : ListAdapter<Good, GoodAdapter.GoodViewHolder>(GoodDiff()) {

    private lateinit var binding: ItemScrollGridBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodViewHolder {
        binding = ItemScrollGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GoodViewHolder(
        private val binding: ItemScrollGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(good: Good) {
            binding.good = good
            itemView.setOnClickListener { onLinkItem(good.linkURL) }
        }
    }
}

class GoodDiff : DiffUtil.ItemCallback<Good>() {
    override fun areItemsTheSame(oldItem: Good, newItem: Good): Boolean {
        return oldItem.brandName == newItem.brandName
    }

    override fun areContentsTheSame(oldItem: Good, newItem: Good): Boolean {
        return oldItem == newItem
    }

}