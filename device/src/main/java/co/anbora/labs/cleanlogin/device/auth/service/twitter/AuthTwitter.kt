package co.anbora.labs.cleanlogin.device.auth.service.twitter

import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.google.firebase.auth.FirebaseAuth
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterLoginButton


class AuthTwitter : Auth {

    private val twitterLoginButton: TwitterLoginButton
    private val callback: AuthCallback
    private val mAuth: FirebaseAuth
    private val twitterCallback: Callback<TwitterSession>

    constructor(mAuth: FirebaseAuth, twitterLoginButton: TwitterLoginButton,
                callback: AuthCallback, twitterCallback: Callback<TwitterSession>) {

        this.mAuth = mAuth
        this.twitterLoginButton = twitterLoginButton
        this.callback = callback
        this.twitterCallback = twitterCallback
    }

    override fun loginRequest() {

        this.twitterLoginButton.callback = this.twitterCallback
    }
}