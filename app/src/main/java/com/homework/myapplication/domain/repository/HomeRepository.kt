package com.homework.myapplication.domain.repository

import com.homework.myapplication.data.model.ListResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getHomeData(): Flow<ListResponse?>
}