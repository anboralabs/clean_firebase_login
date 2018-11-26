package co.anbora.labs.cleanlogin.device.auth.controller.anonymous

import co.anbora.labs.cleanlogin.device.auth.behavior.AnonymousAuthBehavior
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.auth.AuthEnum
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import com.google.firebase.auth.FirebaseAuth

class AnonymousAuthController : AuthController {

    private val authBehavior: AnonymousAuthBehavior
    private val activityResult: ActivityResult

    constructor(authBehavior: AnonymousAuthBehavior, activityResult: ActivityResult) {

        this.authBehavior = authBehavior
        this.activityResult = activityResult
    }

    override fun login() {
        if (this.activityResult.requestCode == AuthEnum.ANONYMOUS.authValue) {
            this.authBehavior.onLoginComplete()
        }
    }
}