package com.anupkumar.instagramclonesample.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.anupkumar.instagramclonesample.R
import com.anupkumar.instagramclonesample.di.component.ActivityComponent
import com.anupkumar.instagramclonesample.ui.base.BaseActivity
import com.anupkumar.instagramclonesample.ui.login.LoginActivity
import com.anupkumar.instagramclonesample.ui.main.MainActivity

class SplashActivity : BaseActivity<SplashViewModel>() {

    companion object{
        const val TAG = "SplashActivity"
    }

    override fun provideLayoutId(): Int = R.layout.activity_splash

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchLogin.observe(this, Observer {
            it.getIfNotHandled().run {
                startActivity(Intent(applicationContext,LoginActivity::class.java))
            }
        })

        viewModel.launchMain.observe(this, Observer {
            it.getIfNotHandled().run {
                startActivity(Intent(applicationContext,MainActivity::class.java))
            }
        })
    }




}