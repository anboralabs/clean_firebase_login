package co.anbora.labs.cleanlogin.device.auth.service.anonymous

import android.app.Activity
import android.content.Context
import android.content.Intent
import co.anbora.labs.cleanlogin.domain.auth.AuthEnum
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import co.anbora.labs.cleanlogin.domain.model.User
import com.google.firebase.auth.FirebaseAuth

class AuthAnonymous : Auth {

    private val context: Activity
    private val mAuth: FirebaseAuth
    private val callback: AuthCallback

    constructor(mAuth: FirebaseAuth, callback: AuthCallback, context: Activity) {
        this.mAuth = mAuth
        this.callback = callback
        this.context = context
    }

    override fun loginRequest() {
        val intentResult = Intent()
        context.setResult(AuthEnum.ANONYMOUS.authValue, intentResult)
    }
}