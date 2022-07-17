package com.homework.myapplication.data.datasource

import com.homework.myapplication.data.model.ListResponse
import kotlinx.coroutines.flow.Flow

interface HomeDataSource {

    fun getListData(): Flow<ListResponse?>
}