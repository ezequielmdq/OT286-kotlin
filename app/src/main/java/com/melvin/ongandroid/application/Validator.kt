package com.melvin.ongandroid.application

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Clase para validar datos
 */
class Validator {
    companion object{
        private const val EMAIL_PATTERN = "^[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@" +
                "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\$"

        fun isEmailValid(email: String): Boolean {
            return Pattern.compile(EMAIL_PATTERN)
                .matcher(email).matches()
        }

        fun isPasswordValid(password: String): Boolean {
            var pattern: Pattern? = null
            var match: Matcher? =null
            pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~`!@#\$%^&*()_+={[}]-|\\:;\"'<,>.?/])(?=\\S+\$).{8,15}\$")
            match = pattern!!.matcher(this.toString())
            return match!!.find()
        }
    }

}