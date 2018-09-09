package co.anbora.labs.cleanlogin.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import co.anbora.labs.cleanlogin.R
import co.anbora.labs.cleanlogin.device.auth.Auth
import co.anbora.labs.cleanlogin.device.auth.controller.AuthCallback
import co.anbora.labs.cleanlogin.device.auth.controller.AuthController
import co.anbora.labs.cleanlogin.device.auth.model.ActivityResult
import co.anbora.labs.cleanlogin.domain.model.User
import co.anbora.labs.cleanlogin.provideFirebaseAuthController
import co.anbora.labs.cleanlogin.provideLoginAuthService
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var authService: Auth
    private lateinit var authController: AuthController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.authService = provideLoginAuthService(this, getString(R.string.default_web_client_id))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        this.authService.login(provideFirebaseAuthController(ActivityResult(this,
                                                            requestCode, data!!), FirebaseAuth.getInstance()),
                                object: AuthCallback {
                                    override fun onSuccess(user: User) {
                                        TODO("do something when the user is find")
                                    }

                                    override fun onError() {
                                        TODO("do something when it can´t login")
                                    }

                                }
                )

    }
}
