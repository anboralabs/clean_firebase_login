package co.anbora.labs.cleanlogin.device.auth.factory.anonymous

import android.app.Activity
import co.anbora.labs.cleanlogin.device.auth.behavior.AnonymousAuthBehavior
import co.anbora.labs.cleanlogin.device.auth.controller.anonymous.AnonymousAuthController
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.device.auth.service.anonymous.AuthAnonymous
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.google.firebase.auth.FirebaseAuth

class AuthAnonymousFactory : AuthFactory {

    private val callback: AuthCallback
    private val context: Activity
    private val authBehavior: AnonymousAuthBehavior

    constructor(context: Activity,
                callback: AuthCallback,
                authBehavior: AnonymousAuthBehavior) {

        this.callback = callback
        this.context = context
        this.authBehavior = authBehavior
    }

    override fun getAuthService(): Auth {
        return AuthAnonymous(callback, context)
    }

    override fun getAuthController(activityResult: ActivityResult, callback: AuthCallback): AuthController {
        return AnonymousAuthController(this.authBehavior, activityResult)
    }
}