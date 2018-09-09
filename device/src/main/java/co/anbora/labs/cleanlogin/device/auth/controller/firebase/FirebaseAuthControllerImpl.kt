package co.anbora.labs.cleanlogin.device.auth.controller.firebase

import android.app.Activity
import android.content.Intent
import co.anbora.labs.cleanlogin.device.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.device.auth.controller.AuthController
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class FirebaseAuthControllerImpl: AuthController {

    private val requestCode: Int
    private val data: Intent
    private val context: Activity

    //Firebase Auth
    private val mAuth: FirebaseAuth

    constructor(activityResult: ActivityResult, mAuth: FirebaseAuth) {

        this.context = activityResult.context
        this.requestCode = activityResult.requestCode
        this.data = activityResult.data
        this.mAuth = mAuth
    }

    override fun login(requestCode: Int, callback: AuthCallback) {
        if (this.requestCode == requestCode) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account, callback)
            } catch (e: ApiException) {
                callback.onError()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount, callback: AuthCallback) {

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(context) { task ->
                    if (task.isSuccessful) {

                        mAuth.currentUser!!.let {
                            val user = User(it.uid, it.displayName)
                            callback.onSuccess(user)
                        }
                    } else {
                        callback.onError()
                    }
                }
    }

}