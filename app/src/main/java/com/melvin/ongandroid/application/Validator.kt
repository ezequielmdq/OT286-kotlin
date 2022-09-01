package com.melvin.ongandroid.application

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
    }

}