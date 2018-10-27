package co.anbora.labs.cleanlogin

import android.app.Activity
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.factory.anonymous.AuthAnonymousFactory
import co.anbora.labs.cleanlogin.device.auth.factory.facebook.AuthFacebookFactory
import co.anbora.labs.cleanlogin.device.auth.factory.google.AuthGoogleFactory
import co.anbora.labs.cleanlogin.device.auth.factory.twitter.AuthTwitterFactory
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth
import com.twitter.sdk.android.core.identity.TwitterLoginButton

fun provideGoogleAuthFactory(context: Activity, defaultWebClientId: String, mAuth: FirebaseAuth): AuthFactory {
    return AuthGoogleFactory(context, defaultWebClientId, mAuth)
}

fun provideFacebookAuthFactory(mAuth: FirebaseAuth, callbackManager: CallbackManager, callback: AuthCallback): AuthFactory {
    return AuthFacebookFactory(mAuth, callbackManager, callback)
}

fun provideTwitterAuthFactory(mAuth: FirebaseAuth, twitterLoginButton: TwitterLoginButton, callback: AuthCallback): AuthFactory {
    return AuthTwitterFactory(mAuth, twitterLoginButton, callback)
}

fun provideAnonymousAuthFactory(mAuth: FirebaseAuth, callback: AuthCallback): AuthFactory {
    return AuthAnonymousFactory(mAuth, callback)
}

