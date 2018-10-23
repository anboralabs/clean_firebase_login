package co.anbora.labs.cleanlogin.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.anbora.labs.cleanlogin.*
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.model.User
import com.facebook.CallbackManager
import com.facebook.login.widget.LoginButton
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth
import com.twitter.sdk.android.core.identity.TwitterLoginButton

class MainActivity : AppCompatActivity() {

    private lateinit var authService: Auth
    private lateinit var authCallback: AuthCallback
    private lateinit var authFactory: AuthFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.authCallback = InfoUserCallback()

        val googleSignInButton = findViewById<SignInButton>(R.id.google_sign_in)
        googleSignInButton.setOnClickListener {

            this.authFactory = provideGoogleAuthFactory(this, getString(R.string.default_web_client_id), FirebaseAuth.getInstance())
            this.setAuthService(authFactory)

        }

        val facebookLoginButton = findViewById<LoginButton>(R.id.facebook_sign_in)
        facebookLoginButton.setReadPermissions("email", "public_profile")
        facebookLoginButton.setOnClickListener {

            this.authFactory = provideFacebookAuthFactory(FirebaseAuth.getInstance(), CallbackManager.Factory.create(), this.authCallback)
            this.setAuthService(authFactory)
        }

        val twitterLoginButton = findViewById<TwitterLoginButton>(R.id.twitter_sign_in)

    }

    private fun setAuthService(factory: AuthFactory) {
        this.authService = factory.getAuthService()
        this.authService.loginRequest()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        this.authService.login(this.authFactory.getAuthController(ActivityResult(this,
                                                            requestCode, resultCode, data!!), this.authCallback))

    }
}
