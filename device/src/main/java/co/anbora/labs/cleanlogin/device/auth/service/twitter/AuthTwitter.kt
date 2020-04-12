package co.anbora.labs.cleanlogin.device.auth.service.twitter

import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterLoginButton


class AuthTwitter : Auth {

    private val twitterLoginButton: TwitterLoginButton
    private val twitterCallback: Callback<TwitterSession>

    constructor(twitterLoginButton: TwitterLoginButton,
                twitterCallback: Callback<TwitterSession>) {

        this.twitterLoginButton = twitterLoginButton
        this.twitterCallback = twitterCallback
    }

    override fun loginRequest() {

        this.twitterLoginButton.callback = this.twitterCallback
    }
}
