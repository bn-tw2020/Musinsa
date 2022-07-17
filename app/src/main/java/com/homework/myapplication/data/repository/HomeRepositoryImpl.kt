package com.homework.myapplication.data.repository

import com.homework.myapplication.data.datasource.HomeDataSource
import com.homework.myapplication.data.model.ListResponse
import com.homework.myapplication.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val assetDataSource: HomeDataSource,
    private val remoteDataSource: HomeDataSource,
) : HomeRepository {

    override fun getHomeData(): Flow<ListResponse?> {
        return remoteDataSource.getListData()
    }
}