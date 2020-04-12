package co.anbora.labs.cleanlogin.device.auth.adapter.twitter

import co.anbora.labs.cleanlogin.device.log.behavior.LogBehavior
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession

class TwitterCallbackAdapter : Callback<TwitterSession> {

    private val logBehavior: LogBehavior

    constructor(logBehavior: LogBehavior) {
        this.logBehavior = logBehavior
    }

    override fun success(result: Result<TwitterSession>?) {
        this.logBehavior.logSuccess()
    }

    override fun failure(exception: TwitterException?) {
        this.logBehavior.logError()
    }
}
