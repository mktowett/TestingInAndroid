package com.mkt.testinginandroidl1.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mkt.testinginandroidl1.data.local.ShoppingItem
import com.mkt.testinginandroidl1.data.remote.responses.ImageResponse
import com.mkt.testinginandroidl1.util.Resource

class FakeShoppingRepository: ShoppingRepository {

    private val shoppingItems = mutableListOf<ShoppingItem>()

    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observeTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(booleanValue: Boolean){
        shouldReturnNetworkError = booleanValue
    }

    private fun refreshLiveData(){
        observableShoppingItems.postValue(shoppingItems)
        observeTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float{
        return shoppingItems.sumOf {
            it.price.toDouble()
        }.toFloat()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return observeTotalPrice
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return observableShoppingItems
    }

    //simulate network call
    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if (shouldReturnNetworkError){
            Resource.error("Error",null)
        }else{
            Resource.success(ImageResponse(listOf(),0,0))
         }
    }
}