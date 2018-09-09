package co.anbora.labs.cleanlogin.device.auth

import co.anbora.labs.cleanlogin.device.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.device.auth.controller.AuthController

interface Auth {

    fun loginRequest();

    fun login(controller: AuthController, callback: AuthCallback)

}