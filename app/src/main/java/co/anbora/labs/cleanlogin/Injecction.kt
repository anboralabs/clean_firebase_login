package co.anbora.labs.cleanlogin

import android.app.Activity
import co.anbora.labs.cleanlogin.device.auth.adapter.facebook.FacebookCallbackAdapter
import co.anbora.labs.cleanlogin.device.auth.adapter.twitter.TwitterCallbackAdapter
import co.anbora.labs.cleanlogin.device.auth.behavior.AnonymousAuthBehavior
import co.anbora.labs.cleanlogin.device.auth.behavior.AnonymousBehavior
import co.anbora.labs.cleanlogin.device.auth.behavior.AuthBehavior
import co.anbora.labs.cleanlogin.device.auth.behavior.CredentialBehavior
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.device.auth.factory.anonymous.AuthAnonymousFactory
import co.anbora.labs.cleanlogin.device.auth.factory.facebook.AuthFacebookFactory
import co.anbora.labs.cleanlogin.device.auth.factory.google.AuthGoogleFactory
import co.anbora.labs.cleanlogin.device.auth.factory.phone.AuthPhoneFactory
import co.anbora.labs.cleanlogin.device.auth.factory.twitter.AuthTwitterFactory
import co.anbora.labs.cleanlogin.device.auth.mapper.UserMapper
import co.anbora.labs.cleanlogin.device.auth.model.AuthPhoneRequest
import co.anbora.labs.cleanlogin.device.log.behavior.LogAuthBehavior
import co.anbora.labs.cleanlogin.device.log.behavior.LogBehavior
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.model.User
import co.anbora.labs.cleanlogin.ui.InfoUserCallback
import co.anbora.labs.cleanlogin.ui.dialog.RequestDialog
import co.anbora.labs.cleanlogin.ui.dialog.phone.PhoneRequestDialog
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import java.util.function.Function

fun provideGoogleAuthFactory(context: Activity, defaultWebClientId: String, authBehavior: AuthBehavior): AuthFactory {
    return AuthGoogleFactory(context, defaultWebClientId, authBehavior)
}

fun provideFacebookAuthFactory(callbackManager: CallbackManager, callback: FacebookCallback<LoginResult>, authBehavior: AuthBehavior): AuthFactory {
    return AuthFacebookFactory(callbackManager, callback, authBehavior)
}

fun provideTwitterAuthFactory(twitterLoginButton: TwitterLoginButton, callback: Callback<TwitterSession>, authBehavior: AuthBehavior): AuthFactory {
    return AuthTwitterFactory(twitterLoginButton, callback, authBehavior)
}

fun provideAnonymousAuthFactory(context: Activity): AuthFactory {
    return AuthAnonymousFactory(context, provideUserCallback(), provideAuthAnonymousAuthBehavior())
}

fun provideUserCallback(): AuthCallback {
    return InfoUserCallback()
}

fun provideAuthBehavior(): AuthBehavior {
    return CredentialBehavior(FirebaseAuth.getInstance(), provideUserCallback(), provideUserMapper())
}

fun provideAuthAnonymousAuthBehavior(): AnonymousAuthBehavior {
    return AnonymousBehavior(FirebaseAuth.getInstance(), provideUserCallback(), provideUserMapper())
}

fun providePhoneAuthFactory(mAuth: FirebaseAuth,
                            authPhoneRequest: AuthPhoneRequest,
                            phoneAuthProvider: PhoneAuthProvider,
                            credential: PhoneAuthCredential,
                            authBehavior: AuthBehavior): AuthFactory {
    return AuthPhoneFactory(mAuth, authPhoneRequest, phoneAuthProvider, credential, authBehavior)
}

fun providePhoneRequestDialog(context: Activity): RequestDialog {
    return PhoneRequestDialog(context)
}

fun provideUserMapper(): Function<FirebaseUser, User> {
    return UserMapper()
}

fun provideFacebookAdapter(context: Activity): FacebookCallback<LoginResult> {
    return FacebookCallbackAdapter(provideLogBehavior(context))
}

fun provideTwitterAdapter(context: Activity): Callback<TwitterSession> {
    return TwitterCallbackAdapter(provideLogBehavior(context))
}

fun provideLogBehavior(context: Activity): LogBehavior {
    return LogAuthBehavior(context)
}
