package co.anbora.labs.cleanlogin.ui.dialog.phone

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import co.anbora.labs.cleanlogin.R
import co.anbora.labs.cleanlogin.device.auth.factory.AuthFactory
import co.anbora.labs.cleanlogin.ui.dialog.RequestDialog

class PhoneRequestDialog : RequestDialog {

    private val alertBuilder: AlertDialog.Builder
    private val layoutInflater: LayoutInflater
    private val context: Activity
    //private val authFactory: AuthFactory
    private val view: View

    private lateinit var alertDialog: AlertDialog

    private lateinit var phoneNumber: TextInputLayout
    private lateinit var phoneCode: TextInputLayout
    private lateinit var sendCode: Button
    private lateinit var verifyCode: Button

    constructor(context: Activity) {

        this.context = context
        //this.authFactory = authFactory

        this.alertBuilder = AlertDialog.Builder(context)
        this.layoutInflater = context.layoutInflater
        this.view = layoutInflater.inflate(R.layout.phone_request_dialog, null)
        this.alertBuilder.setView(view)

        setUpUX(this.view)
    }

    private fun setUpUX(view: View) {

        this.sendCode = view.findViewById(R.id.phone_request_send_code)
        this.verifyCode = view.findViewById(R.id.phone_request_validate_code)

        this.phoneNumber = view.findViewById(R.id.login_request_phone)
        this.phoneNumber.setOnClickListener {
            enablePhoneNumber(false)
            enablePhoneCode(true)
        }

        this.phoneCode = view.findViewById(R.id.login_request_phone_code)
        this.phoneCode.setOnClickListener {
            enablePhoneCode(false)
        }

        this.alertBuilder.setNegativeButton(context.getText(R.string.cancel)) { _, _ ->
            this.hide()
        }
        this.alertDialog = this.alertBuilder.create()
    }

    private fun enablePhoneNumber(enabled: Boolean) {
        this.phoneNumber.isEnabled = enabled
        this.sendCode.isEnabled = enabled
    }

    private fun enablePhoneCode(enabled: Boolean) {
        this.phoneCode.isEnabled = enabled
        this.verifyCode.isEnabled = enabled
    }

    override fun show() {
        this.alertDialog.show()
    }

    override fun hide() {
        this.alertDialog.dismiss()
    }
}
