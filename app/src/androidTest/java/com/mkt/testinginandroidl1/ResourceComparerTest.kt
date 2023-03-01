package com.mkt.testinginandroidl1

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


internal class ResourceComparerTest{

    private lateinit var  resourceComparer : ResourceComparer

    @Before
    fun setup(){
        resourceComparer = ResourceComparer()
    }

    @After
    fun tearDown(){

    }

    @Test
    fun stringResourceSameAsGiveString_returnTrue(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context,R.string.app_name,"TestingInAndroidL1")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceIsDifferentAsGiveString_returnFalse(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(context,R.string.app_name,"TestingInAndroid")
        assertThat(result).isFalse()
    }
}