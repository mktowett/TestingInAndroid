package com.mkt.testinginandroidl1.repositories

import androidx.lifecycle.LiveData
import com.mkt.testinginandroidl1.data.local.ShoppingDao
import com.mkt.testinginandroidl1.data.local.ShoppingItem
import com.mkt.testinginandroidl1.data.remote.PixaBayApi
import com.mkt.testinginandroidl1.data.remote.responses.ImageResponse
import com.mkt.testinginandroidl1.util.Resource
import retrofit2.Response
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixaBayApi: PixaBayApi
): ShoppingRepository {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixaBayApi.searchForImage(imageQuery)
            if (response.isSuccessful){
                response?.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Something went wrong",null)
            }else{
                Resource.error("Something went wrong",null)
            }
        }catch (e: Exception){
            return Resource.error("Something went wrong",null)
        }
    }

}