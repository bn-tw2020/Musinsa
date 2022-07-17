package com.homework.myapplication.presentation.adapter.style

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.homework.myapplication.data.model.Data
import com.homework.myapplication.databinding.ItemStyleSectionBinding
import com.homework.myapplication.presentation.adapter.DataDiff

class StyleSectionAdapter(
    private val onLinkItem: (String?) -> Unit,
    private val onLink: (String?) -> Unit,
    private val onClick: () -> Unit
) : ListAdapter<Data, StyleSectionAdapter.StyleSectionViewHolder>(DataDiff()) {

    private lateinit var binding: ItemStyleSectionBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StyleSectionViewHolder {
        binding =
            ItemStyleSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StyleSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StyleSectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StyleSectionViewHolder(
        private val binding: ItemStyleSectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val styleAdapter = StyleAdapter { url -> onLinkItem(url) }
        private var base = 4
        private val plusItem = 2

        fun bind(data: Data) {
            binding.header = data.header
            binding.footer = data.footer

            binding.rvItems.adapter = styleAdapter
            styleAdapter.submitList(data.contents.styles?.subList(0, base))

            when (binding.footer?.type) {
                "MORE" -> showMore()
                "REFRESH" -> showRefresh()
            }
            binding.layoutFooter.btnRecommend.setOnClickListener { onClickRecommend(data) }
            binding.layoutFooter.btnMore.setOnClickListener { onClickMore(data) }
            binding.layoutHeader.layoutHeader.setOnClickListener { onLink(data.header?.linkURL) }
        }

        private fun onClickMore(data: Data) {
            if (styleAdapter.currentList.size == data.contents.styles?.size) {
                onClick()
                return
            }

            if (base + plusItem < (data.contents.styles?.size ?: 0)) {
                styleAdapter.submitList(data.contents.styles?.subList(0, base + plusItem))
                base += plusItem
            } else {
                styleAdapter.submitList(data.contents.styles)
            }
        }

        private fun onClickRecommend(data: Data) {
            val shuffled = data.contents.styles?.shuffled()
            styleAdapter.submitList(shuffled)
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