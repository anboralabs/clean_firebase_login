package co.anbora.labs.cleanlogin.device.auth.controller

import co.anbora.labs.cleanlogin.domain.model.User

interface AuthCallback {

    fun onSuccess(user: User)

    fun onError()

}