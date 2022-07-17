package com.homework.myapplication.presentation.adapter.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.homework.myapplication.data.model.Data
import com.homework.myapplication.databinding.ItemBannerSectionBinding
import com.homework.myapplication.presentation.adapter.DataDiff

class BannerSectionAdapter(
    private val onLinkItem: (String?) -> Unit,
    private val onLink: (String?) -> Unit
) : ListAdapter<Data, BannerSectionAdapter.BannerSectionViewHolder>(DataDiff()) {

    private lateinit var binding: ItemBannerSectionBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerSectionViewHolder {
        binding =
            ItemBannerSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerSectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BannerSectionViewHolder(
        private val binding: ItemBannerSectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val bannerAdapter = BannerAdapter { url -> onLinkItem(url) }

        fun bind(data: Data) {
            binding.header = data.header
            binding.footer = data.footer

            binding.viewpagerBanner.adapter = bannerAdapter
            binding.tvTotalPage.text = "${data.contents.banners?.size}"
            bannerAdapter.submitList(data.contents.banners)
            binding.viewpagerBanner.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvCurrentPage.text = "${position + 1}"
                }
            })

            when (binding.footer?.type) {
                "MORE" -> showMore()
                "REFRESH" -> showRefresh()
            }
            binding.layoutFooter.btnRecommend.setOnClickListener { onClickRecommend(data) }
            binding.layoutHeader.layoutHeader.setOnClickListener { onLink(data.header?.linkURL) }
        }

        private fun onClickRecommend(data: Data) {
            val shuffled = data.contents.banners?.shuffled()
            bannerAdapter.submitList(shuffled)
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