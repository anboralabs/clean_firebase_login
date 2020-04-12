package co.anbora.labs.cleanlogin.device.auth.adapter.facebook

import co.anbora.labs.cleanlogin.device.log.behavior.LogBehavior
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult

class FacebookCallbackAdapter(private val logBehavior: LogBehavior) : FacebookCallback<LoginResult> {

    override fun onSuccess(result: LoginResult?) {
        logBehavior.logSuccess()
    }

    override fun onCancel() {
        logBehavior.logError()
    }

    override fun onError(error: FacebookException?) {
        logBehavior.logError()
    }
}
