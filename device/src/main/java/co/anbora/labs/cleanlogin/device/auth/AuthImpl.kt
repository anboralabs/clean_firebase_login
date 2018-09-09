package co.anbora.labs.cleanlogin.device.auth

import android.app.Activity
import co.anbora.labs.cleanlogin.device.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.device.auth.controller.AuthController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions



class AuthImpl : Auth {

    private val context: Activity

    //Google Login Request Code
    private val RC_SIGN_IN = 7
    //Google Sign In Client
    private val mGoogleSignInClient: GoogleSignInClient

    constructor(context: Activity, defaultWebClientId: String) {
        this.context = context

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(defaultWebClientId)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this.context, gso)
    }

    override fun loginRequest() {
        val signInIntent = mGoogleSignInClient.signInIntent
        context.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun login(controller: AuthController, callback: AuthCallback) {
        controller.login(RC_SIGN_IN, callback)
    }

}