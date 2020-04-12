package co.anbora.labs.cleanlogin.device.log.behavior

import android.content.Context
import android.util.Log
import co.anbora.labs.cleanlogin.device.R

class LogAuthBehavior(val context: Context) : LogBehavior {

    override fun logSuccess() {
        Log.d(javaClass.name, context.getString(R.string.login_ok))
    }

    override fun logError() {
        Log.d(javaClass.name, context.getString(R.string.login_error))
    }
}
