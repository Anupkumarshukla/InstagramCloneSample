package com.anupkumar.instagramclonesample.di.module

import androidx.lifecycle.ViewModelProviders
import com.anupkumar.instagramclonesample.data.repository.UserRepository
import com.anupkumar.instagramclonesample.ui.base.BaseActivity
import com.anupkumar.instagramclonesample.ui.login.LoginViewModel
import com.anupkumar.instagramclonesample.ui.splash_screen.SplashActivity
import com.anupkumar.instagramclonesample.ui.splash_screen.SplashViewModel
import com.anupkumar.instagramclonesample.utils.ViewModelProviderFactory
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private val activity: BaseActivity<*>) {


    @Provides
    fun provideSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository): SplashViewModel = ViewModelProviders.of(activity,ViewModelProviderFactory(SplashViewModel::class){
        SplashViewModel(schedulerProvider,compositeDisposable,networkHelper,userRepository)
        }).get(SplashViewModel::class.java)


    @Provides
    fun provideLoginViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository): LoginViewModel = ViewModelProviders.of(activity,ViewModelProviderFactory(LoginViewModel::class){
        LoginViewModel(schedulerProvider,compositeDisposable,networkHelper,userRepository)
    }).get(LoginViewModel::class.java)






}