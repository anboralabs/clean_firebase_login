package co.anbora.labs.cleanlogin.device.auth.service.facebook

import co.anbora.labs.cleanlogin.device.auth.behavior.AuthBehavior
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import co.anbora.labs.cleanlogin.domain.model.User
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth


class AuthFacebook: Auth {

    private val facebookCallbackManager: CallbackManager
    private val authBehavior: AuthBehavior
    private val mAuth: FirebaseAuth

    constructor(mAuth: FirebaseAuth, callbackManager: CallbackManager, authBehavior: AuthBehavior) {

        this.mAuth = mAuth
        this.facebookCallbackManager = callbackManager
        this.authBehavior = authBehavior
    }

    override fun loginRequest() {
        LoginManager.getInstance().registerCallback(facebookCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                result?.accessToken?.let {
                    authBehavior.onLoginComplete(FacebookAuthProvider.getCredential(it.token))
                }
            }

            override fun onCancel() {
                authBehavior.onLoginError()
            }

            override fun onError(error: FacebookException?) {
                authBehavior.onLoginError()
            }
        })
    }
}