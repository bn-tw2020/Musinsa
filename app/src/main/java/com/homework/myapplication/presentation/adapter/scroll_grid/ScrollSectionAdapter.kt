package com.homework.myapplication.presentation.adapter.scroll_grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.homework.myapplication.data.model.Data
import com.homework.myapplication.databinding.ItemScrollSectionBinding
import com.homework.myapplication.presentation.adapter.DataDiff

class ScrollSectionAdapter(
    private val onLinkItem: (String?) -> Unit,
    private val onLink: (String?) -> Unit
) : ListAdapter<Data, ScrollSectionAdapter.ScrollSectionViewHolder>(DataDiff()) {

    private lateinit var binding: ItemScrollSectionBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScrollSectionViewHolder {
        binding =
            ItemScrollSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScrollSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScrollSectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ScrollSectionViewHolder(
        private val binding: ItemScrollSectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val goodAdapter = GoodAdapter { url -> onLinkItem(url) }

        fun bind(data: Data) {
            binding.header = data.header
            binding.data = data
            binding.footer = data.footer

            binding.rvItems.adapter = goodAdapter
            goodAdapter.submitList(data.contents.goods)

            when (binding.footer?.type) {
                "MORE" -> showMore()
                "REFRESH" -> showRefresh()
            }
            binding.layoutFooter.btnRecommend.setOnClickListener { onClickRecommend(data) }
            binding.layoutHeader.layoutHeader.setOnClickListener { onLink(data.header?.linkURL) }
        }

        private fun onClickRecommend(data: Data) {
            val shuffled = data.contents.goods?.shuffled()
            goodAdapter.submitList(shuffled)
        }

        private fun showMore() {
            binding.layoutFooter.btnMore.isVisible = true
            binding.layoutFooter.btnRecommend.isGone = true
        }

        private fun showRefresh() {
            binding.layoutFooter.btnRecommend.isVisible = true
            binding.layoutFooter.btnMore.isGone = true
        }
    }
}