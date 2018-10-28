package co.anbora.labs.cleanlogin.device.auth.behavior

import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginBehavior(val mAuth: FirebaseAuth, val callback: AuthCallback): AuthBehavior {
    override fun onLoginComplete(task: Task<AuthResult>) {
        if (task.isSuccessful) {

            mAuth.currentUser?.let {
                val user = User.Builder()
                        .id(it.uid)
                        .name(it.displayName)
                        .build()
                callback.onSuccess(user)
            }
        } else {
            callback.onError()
        }
    }
}