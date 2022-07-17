package com.homework.myapplication.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.homework.myapplication.data.model.Data

class DataDiff : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.contents == newItem.contents
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

}