package co.anbora.labs.cleanlogin.device.auth.factory.facebook

import co.anbora.labs.cleanlogin.device.auth.behavior.AuthBehavior
import co.anbora.labs.cleanlogin.device.auth.controller.facebook.FacebookAuthController
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.device.auth.service.facebook.AuthFacebook
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginResult

class AuthFacebookFactory : AuthFactory {

    private val callbackManager: CallbackManager
    private val facebookCallback: FacebookCallback<LoginResult>
    private val authBehavior: AuthBehavior

    constructor(callbackManager: CallbackManager,
                facebookCallback: FacebookCallback<LoginResult>,
                authBehavior: AuthBehavior) {

        this.callbackManager = callbackManager
        this.facebookCallback = facebookCallback
        this.authBehavior = authBehavior
    }

    override fun getAuthService(): Auth {
        return AuthFacebook(callbackManager, facebookCallback)
    }

    override fun getAuthController(activityResult: ActivityResult, callback: AuthCallback): AuthController {
        return FacebookAuthController(activityResult, callbackManager, authBehavior)
    }
}