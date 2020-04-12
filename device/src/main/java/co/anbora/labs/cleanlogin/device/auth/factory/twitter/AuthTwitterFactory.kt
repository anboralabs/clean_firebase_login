package co.anbora.labs.cleanlogin.device.auth.factory.twitter

import co.anbora.labs.cleanlogin.device.auth.behavior.AuthBehavior
import co.anbora.labs.cleanlogin.device.auth.controller.twitter.TwitterAuthController
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.device.auth.service.twitter.AuthTwitter
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterLoginButton

class AuthTwitterFactory : AuthFactory {

    private val twitterLoginButton: TwitterLoginButton
    private val twitterCallback: Callback<TwitterSession>
    private val authBehavior: AuthBehavior

    constructor(twitterLoginButton: TwitterLoginButton,
                twitterCallback: Callback<TwitterSession>,
                authBehavior: AuthBehavior) {

        this.twitterLoginButton = twitterLoginButton
        this.twitterCallback = twitterCallback
        this.authBehavior = authBehavior
    }

    override fun getAuthService(): Auth {
        return AuthTwitter(twitterLoginButton, this.twitterCallback)
    }

    override fun getAuthController(activityResult: ActivityResult, callback: AuthCallback): AuthController {
        return TwitterAuthController(activityResult, twitterLoginButton, authBehavior)
    }
}
