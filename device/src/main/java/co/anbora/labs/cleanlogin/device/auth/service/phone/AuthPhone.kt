package co.anbora.labs.cleanlogin.device.auth.service.phone

import android.app.Activity
import co.anbora.labs.cleanlogin.device.auth.model.AuthPhoneRequest
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class AuthPhone : Auth {

    private val phoneNumber: String
    private val context: Activity
    private val mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private val phoneAuthProvider: PhoneAuthProvider

    constructor(authPhoneRequest: AuthPhoneRequest, phoneAuthProvider: PhoneAuthProvider) {
        this.phoneNumber = authPhoneRequest.phoneNumber
        this.context = authPhoneRequest.context
        this.mCallbacks = authPhoneRequest.phoneAuthCallback
        this.phoneAuthProvider = phoneAuthProvider
    }

    override fun loginRequest() {
        phoneAuthProvider.verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                context,
                mCallbacks)
    }
}
