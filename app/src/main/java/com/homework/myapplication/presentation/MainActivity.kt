package com.homework.myapplication.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import com.homework.myapplication.R
import com.homework.myapplication.databinding.ActivityMainBinding
import com.homework.myapplication.presentation.Type.*
import com.homework.myapplication.presentation.adapter.banner.BannerSectionAdapter
import com.homework.myapplication.presentation.adapter.scroll_grid.GridSectionAdapter
import com.homework.myapplication.presentation.adapter.scroll_grid.ScrollSectionAdapter
import com.homework.myapplication.presentation.adapter.style.StyleSectionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()

    private val bannerSectionAdapter = BannerSectionAdapter(
        onLinkItem = { url -> intentBrowser(url) },
        onLink = { url -> intentBrowser(url) }
    )
    private val gridSectionAdapter = GridSectionAdapter(
        onLink = { url -> intentBrowser(url) },
        onLinkItem = { url -> intentBrowser(url) },
        onClick = { toastMessage("더 이상 데이터가 없습니다.") }
    )
    private val scrollSectionAdapter = ScrollSectionAdapter(
        onLinkItem = { url -> intentBrowser(url) },
        onLink = { url -> intentBrowser(url) }
    )
    private val styleSectionAdapter = StyleSectionAdapter(
        onLinkItem = { url -> intentBrowser(url) },
        onLink = { url -> intentBrowser(url) },
        onClick = { toastMessage("더 이상 데이터가 없습니다.") }
    )
    private val concatAdapter = ConcatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.getHomeData()
        observeData()

    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeData.collectLatest {
                    when (it) {
                        is UiState.Success -> handlerSuccess(it)
                        is UiState.Loading, UiState.Empty -> handlerLoading()
                        is UiState.Error -> handlerError()
                    }
                }
            }
        }
    }

    private fun handlerError() {
        Toast.makeText(this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun handlerLoading() {
        binding.pbLoading.isVisible = true
    }

    private fun handlerSuccess(state: UiState.Success) {
        binding.pbLoading.isGone = true
        val dataList = state.list.data
        dataList.forEach { data ->
            when (data.contents.type) {
                GRID.type -> {
                    gridSectionAdapter.submitList(listOf(data))
                    concatAdapter.addAdapter(gridSectionAdapter)
                }
                SCROLL.type -> {
                    scrollSectionAdapter.submitList(listOf(data))
                    concatAdapter.addAdapter(scrollSectionAdapter)
                }
                BANNER.type -> {
                    bannerSectionAdapter.submitList(listOf(data))
                    concatAdapter.addAdapter(bannerSectionAdapter)
                }
                STYLE.type -> {
                    styleSectionAdapter.submitList(listOf(data))
                    concatAdapter.addAdapter(styleSectionAdapter)
                }
            }
        }
        binding.rvHome.adapter = concatAdapter
    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun intentBrowser(url: String?) {
        if (!url.isNullOrBlank()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}