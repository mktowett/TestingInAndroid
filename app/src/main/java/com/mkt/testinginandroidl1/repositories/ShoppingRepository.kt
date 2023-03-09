package com.mkt.testinginandroidl1.repositories

import androidx.lifecycle.LiveData
import com.mkt.testinginandroidl1.data.local.ShoppingItem
import com.mkt.testinginandroidl1.data.remote.responses.ImageResponse
import com.mkt.testinginandroidl1.util.Resource
import retrofit2.Response

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeTotalPrice(): LiveData<Float>

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>

}