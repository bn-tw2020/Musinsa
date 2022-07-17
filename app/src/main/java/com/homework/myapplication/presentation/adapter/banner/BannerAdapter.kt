package com.homework.myapplication.presentation.adapter.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.homework.myapplication.data.model.Banner
import com.homework.myapplication.databinding.ItemBannerBinding

class BannerAdapter(
    private val onLinkItem: (String?) -> Unit
) : ListAdapter<Banner, BannerAdapter.BannerViewHolder>(BannerDiff()) {

    private lateinit var binding: ItemBannerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BannerViewHolder(
        private val binding: ItemBannerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(banner: Banner) {
            binding.banner = banner
            itemView.setOnClickListener { onLinkItem(banner.linkURL) }
        }
    }
}

class BannerDiff : DiffUtil.ItemCallback<Banner>() {
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }

}