package co.anbora.labs.cleanlogin

import android.app.Activity
import co.anbora.labs.cleanlogin.device.auth.Auth
import co.anbora.labs.cleanlogin.device.auth.AuthImpl
import co.anbora.labs.cleanlogin.device.auth.controller.AuthController
import co.anbora.labs.cleanlogin.device.auth.controller.firebase.FirebaseAuthControllerImpl
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import com.google.firebase.auth.FirebaseAuth

fun provideFirebaseAuthController(activityResult: ActivityResult, mAuth: FirebaseAuth): AuthController {
    return FirebaseAuthControllerImpl(activityResult, mAuth)
}

fun provideLoginAuthService(context: Activity, defaultWebClientId: String): Auth {
    return AuthImpl(context, defaultWebClientId)
}

