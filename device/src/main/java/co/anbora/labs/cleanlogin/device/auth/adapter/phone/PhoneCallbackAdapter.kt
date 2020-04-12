package co.anbora.labs.cleanlogin.device.auth.adapter.phone

import co.anbora.labs.cleanlogin.device.auth.behavior.AuthBehavior
import co.anbora.labs.cleanlogin.device.log.behavior.LogBehavior
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class PhoneCallbackAdapter: PhoneAuthProvider.OnVerificationStateChangedCallbacks {

    private val authBehavior: AuthBehavior

    constructor(authBehavior: AuthBehavior) {
        this.authBehavior = authBehavior
    }

    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
        authBehavior.onLoginComplete(credential)
    }

    override fun onVerificationFailed(p0: FirebaseException?) {
        authBehavior.onLoginError()
    }

    override fun onCodeSent(verificationId: String?, token: PhoneAuthProvider.ForceResendingToken?) {

    }
}
