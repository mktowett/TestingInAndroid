package com.mkt.testinginandroidl1

object RegistrationUtil {
    private val existingUser = listOf("Mark","Mercy")
    /**
     * input is invalid if
     * username/password is empty
     * username is taken
     * confirm password is not matching password
     */
    fun validateRegistrationInput(username: String, password: String, confirmPassword: String): Boolean{
        if (username.isEmpty() || password.isEmpty()){
            return false
        }
        if (username in existingUser){
            return false
        }
        if (password != confirmPassword){
            return false
        }
        if (password.count { it.isDigit() } < 2){
            return false
        }
        return true
    }
}