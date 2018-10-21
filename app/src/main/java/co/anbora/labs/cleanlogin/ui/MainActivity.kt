package co.anbora.labs.cleanlogin.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.anbora.labs.cleanlogin.R
import co.anbora.labs.cleanlogin.domain.auth.service.Auth
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.domain.auth.controller.AuthController
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.model.User
import co.anbora.labs.cleanlogin.provideFirebaseAuthController
import co.anbora.labs.cleanlogin.provideLoginAuthService
import com.facebook.login.widget.LoginButton
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var authService: Auth
    private lateinit var authController: AuthController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val googleSignInButton = findViewById<SignInButton>(R.id.google_sign_in)
        googleSignInButton.setOnClickListener {
            this.authService = provideLoginAuthService(this, getString(R.string.default_web_client_id))
            this.authService.loginRequest()
        }

        val facebookLoginButton = findViewById<LoginButton>(R.id.facebook_sign_in)
        facebookLoginButton.setReadPermissions("email", "public_profile")
        facebookLoginButton.setOnClickListener {
            //TO DO assign value to authservice
            this.authService.loginRequest()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        this.authService.login(provideFirebaseAuthController(ActivityResult(this,
                                                            requestCode, resultCode, data!!),
                FirebaseAuth.getInstance(),
                object : AuthCallback{
            override fun onSuccess(user: User) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }))

    }
}
