package co.anbora.labs.cleanlogin.device.auth.behavior

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthBehavior {

    fun onLoginComplete(task: Task<AuthResult>)

}