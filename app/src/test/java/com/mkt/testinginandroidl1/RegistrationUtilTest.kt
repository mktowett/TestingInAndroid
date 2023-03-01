package com.mkt.testinginandroidl1

import com.google.common.truth.Truth.assertThat
import org.junit.Test


internal class RegistrationUtilTest {

    @Test
    fun `empty username returns false`(){
        val result  = RegistrationUtil.validateRegistrationInput(
            "",
            "1234",
            "1234"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated password returns true`(){
        val result  = RegistrationUtil.validateRegistrationInput(
            "Marvin",
            "1234",
            "1234"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun ` username already exists returns false`(){
        val result  = RegistrationUtil.validateRegistrationInput(
            "Mark",
            "1234",
            "1234"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        val result  = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password was incorrectly repeated returns false`(){
        val result  = RegistrationUtil.validateRegistrationInput(
            "Carl",
            "1234567",
            "1234"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `less than 2 digit password returns false`(){
        val result  = RegistrationUtil.validateRegistrationInput(
            "Mark",
            "1234",
            "1234"
        )
        assertThat(result).isFalse()
    }
}