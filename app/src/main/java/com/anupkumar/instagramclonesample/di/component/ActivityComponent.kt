package com.anupkumar.instagramclonesample.di.component

import com.anupkumar.instagramclonesample.di.ActivityScope
import com.anupkumar.instagramclonesample.di.module.ActivityModule
import com.anupkumar.instagramclonesample.di.module.ApplicationModule
import com.anupkumar.instagramclonesample.ui.login.LoginActivity
import com.anupkumar.instagramclonesample.ui.main.MainActivity
import com.anupkumar.instagramclonesample.ui.splash_screen.SplashActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: MainActivity)
}