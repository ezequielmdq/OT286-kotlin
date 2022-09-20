package com.melvin.ongandroid.businesslogic

import androidx.lifecycle.LiveData
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

/** Objeto para realizar el log de eventos en firebase*/

object FirebaseLog {

    fun logSliderSuccess() {
        Firebase.analytics.logEvent("slider_retrieve_success") {
            param("log_slide", "Log Slide")
            param("text", "La conexión con el servidor fue satisfactoria.")
        }
    }

    fun logSliderError() {
        Firebase.analytics.logEvent("slider_retrieve_error") {
            param("log_slide", "Log Slide")
            param("text", "La conexión con el servidor no pudo ser establecida.")
        }

    }

    fun logNovedadesSuccess() {
        Firebase.analytics.logEvent("last_news_retrieve_success") {
            param("log_news", "Log News")
            param("text", "La conexión con el servidor fue satisfactoria.")
        }

    }

    fun logNovedadesError() {
        Firebase.analytics.logEvent("last_news_retrieve_error") {
            param("log_news", "Log News")
            param("text", "La conexión con el servidor no pudo ser establecida.")
        }
    }

    fun logTestimonioSuccess() {
        Firebase.analytics.logEvent("testimonios_retrieve_success") {
            param("log_testimonios", "Log Testimonios")
            param("text", "La conexión con el servidor fue satisfactoria.")
        }
    }

    fun logTestimonioError() {
        Firebase.analytics.logEvent("testimonies_retrieve_error") {
            param("log_testimonios", "Log Testimonios")
            param("text", "La conexión con el servidor no pudo ser establecida.")
        }
    }


    fun logMiembrosSuccess() {
        Firebase.analytics.logEvent("members_retrieve_success") {
            param("log_miembros", "Log Miembros")
            param("text", "La conexión con el servidor fue satisfactoria.")
        }
    }

    fun logMiembrosError() {
        Firebase.analytics.logEvent("members_retrieve_error") {
            param("log_miembros", "Log Miembros")
            param("text", "La conexión con el servidor no pudo ser establecida.")
        }
    }

    fun logMiembrosClisk() {
        Firebase.analytics.logEvent("member_pressed") {
            param("text", "Member pressed")
        }
    }


    fun logLogInPressed() {
        Firebase.analytics.logEvent("log_in_pressed") {
            param("log_login", "Log Login")
            param("text", "El boton Log in ha sido presionado.")
        }
    }

    fun logSignUpPressed() {
        Firebase.analytics.logEvent("sign_up_pressed") {
            param("log_login", "Log Login")
            param("text", "El boton Sign up in ha sido presionado.")
        }
    }

    fun logGmailPressed() {
        Firebase.analytics.logEvent("gmail_pressed") {
            param("log_login", "Log Login")
            param("text", "El boton de inicio de sesion con google ha sido presionado.")
        }
    }

    fun logFacebookPressed() {
        Firebase.analytics.logEvent("facebook_pressed") {
            param("log_login", "Log Login")
            param("text", "El boton de inicio de sesion con facebook ha sido presionado.")
        }
    }

    fun logLogInSuccess() {
        Firebase.analytics.logEvent("log_in_success") {
            param("log_login", "Log Login")
            param("text", "El usuario se ha logueado exitosamente.")
        }
    }

    fun logLogInError() {
        Firebase.analytics.logEvent("log_in_error") {
            param("log_login", "Log Login")
            param("text", "Ha ocurrido un error con el logueo del usuario.")
        }
    }


    fun logSignUpClick() {
        Firebase.analytics.logEvent("register_pressed") {
            param("text", "Register_pressed")
        }
    }

    fun logSignUpSuccess() {
        Firebase.analytics.logEvent("sign_up_success") {
            param("log_sign_up", "Log_sign_up")
            param("text", "La conexión con el servidor fue satisfactoria.")
        }
    }

    fun logSignUpError() {
        Firebase.analytics.logEvent("sign_up_error") {
            param("log_sign_up", "Log_sign_up")
            param("text", "La conexión con el servidor no pudo ser establecida.")

        }
    }

}

