package co.anbora.labs.cleanlogin.device.auth.factory.google

import android.app.Activity
import co.anbora.labs.cleanlogin.device.auth.behavior.AuthBehavior
import co.anbora.labs.cleanlogin.device.auth.controller.google.GoogleAuthController
import co.anbora.labs.cleanlogin.device.auth.service.google.AuthGoogle
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.google.firebase.auth.FirebaseAuth

class AuthGoogleFactory : AuthFactory {


    private val authBehavior: AuthBehavior
    private val context: Activity
    private val defaultWebClientId: String

    constructor(context: Activity, defaultWebClientId: String, authBehavior: AuthBehavior) {

        this.context = context
        this.defaultWebClientId = defaultWebClientId
        this.authBehavior = authBehavior
    }

    override fun getAuthService(): Auth {
        return AuthGoogle(context, defaultWebClientId)
    }

    override fun getAuthController(activityResult: ActivityResult, callback: AuthCallback): AuthController {
        return GoogleAuthController(activityResult, this.authBehavior)
    }
}