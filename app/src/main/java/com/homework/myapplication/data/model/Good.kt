package com.homework.myapplication.data.model

data class Good(
    val brandName: String,
    val hasCoupon: Boolean,
    val linkURL: String,
    val price: Int,
    val saleRate: Int,
    val thumbnailURL: String
)