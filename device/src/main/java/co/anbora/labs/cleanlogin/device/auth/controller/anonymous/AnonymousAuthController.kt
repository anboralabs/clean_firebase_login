package co.anbora.labs.cleanlogin.device.auth.controller.anonymous

import co.anbora.labs.cleanlogin.device.auth.behavior.AnonymousAuthBehavior
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import com.google.firebase.auth.FirebaseAuth

class AnonymousAuthController : AuthController {

    private val mAuth: FirebaseAuth
    private val authBehavior: AnonymousAuthBehavior

    constructor(mAuth: FirebaseAuth, authBehavior: AnonymousAuthBehavior) {
        this.mAuth = mAuth
        this.authBehavior = authBehavior
    }

    override fun login() {
        this.authBehavior.onLoginComplete()
    }
}