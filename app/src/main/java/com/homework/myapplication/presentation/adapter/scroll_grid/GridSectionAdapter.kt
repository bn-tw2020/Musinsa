package com.homework.myapplication.presentation.adapter.scroll_grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.homework.myapplication.data.model.Data
import com.homework.myapplication.databinding.ItemGridSectionBinding
import com.homework.myapplication.presentation.adapter.DataDiff

class GridSectionAdapter(
    private val onLink: (String?) -> Unit,
    private val onLinkItem: (String?) -> Unit,
    private val onClick: () -> Unit
) : ListAdapter<Data, GridSectionAdapter.GridSectionViewHolder>(DataDiff()) {

    private lateinit var binding: ItemGridSectionBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridSectionViewHolder {
        binding =
            ItemGridSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridSectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GridSectionViewHolder(
        private val binding: ItemGridSectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val goodAdapter = GoodAdapter { url -> onLinkItem(url) }
        private var base = 6
        private val plusItem = 3

        fun bind(data: Data) {
            binding.header = data.header
            binding.footer = data.footer

            binding.rvItems.adapter = goodAdapter
            goodAdapter.submitList(data.contents.goods?.subList(0, base))

            when (binding.footer?.type) {
                "MORE" -> showMore()
                "REFRESH" -> showRefresh()
            }
            binding.layoutFooter.btnRecommend.setOnClickListener { onClickRecommend(data) }
            binding.layoutFooter.btnMore.setOnClickListener { onClickMore(data) }
            binding.layoutHeader.layoutHeader.setOnClickListener { onLink(data.header?.linkURL) }
        }

        private fun onClickMore(data: Data) {
            if (goodAdapter.currentList.size == data.contents.goods?.size) {
                onClick()
                return
            }
            if (base + plusItem < ((data.contents.goods?.size) ?: 0)) {
                goodAdapter.submitList(data.contents.goods?.subList(0, base + plusItem))
                base += plusItem
            } else {
                goodAdapter.submitList(data.contents.goods)
            }
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