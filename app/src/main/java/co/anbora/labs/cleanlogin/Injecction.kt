package co.anbora.labs.cleanlogin

import android.app.Activity
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import co.anbora.labs.cleanlogin.device.auth.service.google.AuthGoogle
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.device.auth.controller.google.GoogleAuthController
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.factory.facebook.AuthFacebookFactory
import co.anbora.labs.cleanlogin.device.auth.factory.google.AuthGoogleFactory
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth

fun provideGoogleAuthFactory(context: Activity, defaultWebClientId: String, mAuth: FirebaseAuth): AuthFactory {
    return AuthGoogleFactory(context, defaultWebClientId, mAuth)
}

fun provideFacebookAuthFactory(mAuth: FirebaseAuth, callbackManager: CallbackManager, callback: AuthCallback): AuthFactory {
    return AuthFacebookFactory(mAuth, callbackManager, callback)
}

