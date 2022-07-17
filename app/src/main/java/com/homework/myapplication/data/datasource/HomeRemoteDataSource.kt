package com.homework.myapplication.data.datasource

import com.homework.myapplication.data.model.ListResponse
import com.homework.myapplication.data.network.MusinsaService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(
    private val api: MusinsaService
) : HomeDataSource {


    override fun getListData(): Flow<ListResponse?> = flow {
        emit(api.getHomeData())
    }
}