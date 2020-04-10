package co.anbora.labs.cleanlogin.device.auth.factory.phone

import co.anbora.labs.cleanlogin.device.auth.behavior.AuthBehavior
import co.anbora.labs.cleanlogin.device.auth.controller.phone.PhoneAuthController
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.device.auth.model.AuthPhoneCredential
import co.anbora.labs.cleanlogin.device.auth.model.AuthPhoneRequest
import co.anbora.labs.cleanlogin.device.auth.service.phone.AuthPhone
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.google.firebase.auth.PhoneAuthProvider

class AuthPhoneFactory: AuthFactory {

    private val authPhoneRequest: AuthPhoneRequest
    private val phoneAuthProvider: PhoneAuthProvider
    private val authBehavior: AuthBehavior

    constructor(authPhoneRequest: AuthPhoneRequest,
                phoneAuthProvider: PhoneAuthProvider,
                authBehavior: AuthBehavior) {

        this.authPhoneRequest = authPhoneRequest
        this.phoneAuthProvider = phoneAuthProvider
        this.authBehavior = authBehavior
    }

    override fun getAuthService(): Auth {
        return AuthPhone(authPhoneRequest, phoneAuthProvider)
    }

    override fun getAuthController(activityResult: ActivityResult, callback: AuthCallback): AuthController {
        return PhoneAuthController(AuthPhoneCredential(
                activityResult.data.getStringExtra("verificationId"),
                activityResult.data.getStringExtra("code")), authBehavior)
    }
}