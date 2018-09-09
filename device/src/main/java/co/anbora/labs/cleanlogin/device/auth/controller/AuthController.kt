package co.anbora.labs.cleanlogin.device.auth.controller

interface AuthController {

    fun login(requestCode: Int, callback: AuthCallback)

}