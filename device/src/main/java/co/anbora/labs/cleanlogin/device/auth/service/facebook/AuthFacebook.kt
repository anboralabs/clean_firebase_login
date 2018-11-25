package co.anbora.labs.cleanlogin.device.auth.service.facebook

import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult


class AuthFacebook: Auth {

    private val facebookCallbackManager: CallbackManager
    private val facebookCallback: FacebookCallback<LoginResult>

    constructor(callbackManager: CallbackManager, facebookCallback: FacebookCallback<LoginResult>) {

        this.facebookCallbackManager = callbackManager
        this.facebookCallback = facebookCallback
    }

    override fun loginRequest() {
        LoginManager.getInstance().registerCallback(facebookCallbackManager, this.facebookCallback)
    }
}