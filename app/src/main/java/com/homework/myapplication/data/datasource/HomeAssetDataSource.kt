package com.homework.myapplication.data.datasource

import com.google.gson.Gson
import com.homework.myapplication.data.model.ListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeAssetDataSource @Inject constructor(
    private val assetLoader: AssetLoader
) : HomeDataSource {

    private val gson = Gson()

    override fun getListData(): Flow<ListResponse?> = flow {
        emit(assetLoader.getJsonString("list.json")?.let { jsonString ->
            gson.fromJson(jsonString, ListResponse::class.java)
        })
    }
}