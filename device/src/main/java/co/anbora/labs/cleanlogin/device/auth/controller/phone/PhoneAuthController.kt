package co.anbora.labs.cleanlogin.device.auth.controller.phone

import co.anbora.labs.cleanlogin.device.auth.behavior.AuthBehavior
import co.anbora.labs.cleanlogin.device.auth.model.AuthPhoneCredential
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import com.google.firebase.auth.PhoneAuthProvider


class PhoneAuthController : AuthController {

    private val authBehavior: AuthBehavior
    private val credential: AuthPhoneCredential

    constructor(credential: AuthPhoneCredential, authBehavior: AuthBehavior) {

        this.authBehavior = authBehavior
        this.credential = credential
    }

    override fun login() {
        authBehavior.onLoginComplete(PhoneAuthProvider.getCredential(credential.verificationId, credential.code))
    }
}
