package com.mkt.testinginandroidl1.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mkt.testinginandroidl1.data.local.ShoppingItemDatabase
import com.mkt.testinginandroidl1.data.remote.PixaBayApi
import com.mkt.testinginandroidl1.util.Constants.BASE_URL
import com.mkt.testinginandroidl1.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase( @ApplicationContext context: Context ) = Room.databaseBuilder(
        context, ShoppingItemDatabase::class.java, DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixaBayApi(): PixaBayApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixaBayApi::class.java)
    }

}