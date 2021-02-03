package com.anupkumar.instagramclonesample.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.anupkumar.instagramclonesample.R
import com.anupkumar.instagramclonesample.di.component.ActivityComponent
import com.anupkumar.instagramclonesample.ui.base.BaseActivity
import com.anupkumar.instagramclonesample.ui.main.MainActivity
import com.anupkumar.instagramclonesample.utils.common.Status
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginViewModel>() {

    companion object{
        const val TAG = "LoginActivity"
    }

    override fun provideLayoutId(): Int  = R.layout.activity_login


    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        et_email.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.onEmailChange(p0.toString())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        et_password.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.onPasswordChange(p0.toString())
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        bt_login.setOnClickListener { viewModel.onLogin() }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.launchMain.observe(this, Observer {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext,MainActivity::class.java))
                finish()
            }
        })

        viewModel.emailField.observe(this, Observer {
            if (et_email.text.toString() != it)et_email.setText(it)
        })

        viewModel.passwordField.observe(this, Observer {
            if(et_password.text.toString() != it)et_password.setText(it)
        })

        viewModel.emailValidation.observe(this, Observer {
            when(it.status){
                Status.ERROR-> layout_email.error = it.data?.run { getString(this) }
                else -> layout_email.isErrorEnabled = false
            }
        })

        viewModel.passwordValidation.observe(this, Observer {
            when(it.status){
                Status.ERROR-> layout_password.error = it.data?.run { getString(this) }
                else -> layout_password.isErrorEnabled = false
            }
        })

        viewModel.loggingIn.observe(this, Observer {
            pb_loading.visibility = if (it) View.VISIBLE else View.GONE
        })

    }




}