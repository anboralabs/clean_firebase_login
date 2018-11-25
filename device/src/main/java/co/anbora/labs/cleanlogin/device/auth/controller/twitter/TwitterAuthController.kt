package co.anbora.labs.cleanlogin.device.auth.controller.twitter

import co.anbora.labs.cleanlogin.device.auth.behavior.AuthBehavior
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.auth.AuthEnum
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import com.google.firebase.auth.TwitterAuthProvider
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.identity.TwitterLoginButton

class TwitterAuthController : AuthController {

    private val activityResult: ActivityResult
    private val twitterLoginButton: TwitterLoginButton
    private val authBehavior: AuthBehavior

    constructor(activityResult: ActivityResult, twitterLoginButton: TwitterLoginButton, authBehavior: AuthBehavior) {

        this.twitterLoginButton = twitterLoginButton
        this.activityResult = activityResult
        this.authBehavior = authBehavior
    }

    override fun login() {

        TwitterCore.getInstance().sessionManager.activeSession.authToken.let {
            this.authBehavior.onLoginComplete(TwitterAuthProvider.getCredential(it.token,
                    it.secret))
        }

        this.twitterLoginButton.onActivityResult(activityResult.requestCode, activityResult.resultCode, activityResult.data)
    }
}