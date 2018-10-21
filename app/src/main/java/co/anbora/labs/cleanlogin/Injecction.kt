package co.anbora.labs.cleanlogin

import android.app.Activity
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import co.anbora.labs.cleanlogin.device.auth.service.google.AuthGoogle
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.device.auth.controller.google.GoogleAuthController
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import com.google.firebase.auth.FirebaseAuth

fun provideFirebaseAuthController(activityResult: ActivityResult, mAuth: FirebaseAuth, callback: AuthCallback): AuthController {
    return GoogleAuthController(activityResult, mAuth, callback)
}

fun provideLoginAuthService(context: Activity, defaultWebClientId: String): Auth {
    return AuthGoogle(context, defaultWebClientId)
}

