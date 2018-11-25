package co.anbora.labs.cleanlogin.device.auth.controller.facebook

import co.anbora.labs.cleanlogin.device.auth.behavior.AuthBehavior
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.auth.AuthEnum
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.google.firebase.auth.FacebookAuthProvider

class FacebookAuthController: AuthController {

    private val mCallbackManager: CallbackManager
    private val activityResult: ActivityResult
    private val authBehavior: AuthBehavior

    constructor(activityResult: ActivityResult, callbackManager: CallbackManager, authBehavior: AuthBehavior) {

        this.mCallbackManager = callbackManager
        this.activityResult = activityResult
        this.authBehavior = authBehavior
    }

    override fun login() {

        authBehavior.onLoginComplete(FacebookAuthProvider.getCredential(AccessToken.getCurrentAccessToken().token))
        this.mCallbackManager.onActivityResult(activityResult.requestCode, activityResult.resultCode, activityResult.data)
    }
}