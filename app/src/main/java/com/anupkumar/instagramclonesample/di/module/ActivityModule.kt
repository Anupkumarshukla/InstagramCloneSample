package com.anupkumar.instagramclonesample.di.module

import androidx.lifecycle.ViewModelProviders
import com.anupkumar.instagramclonesample.data.repository.PhotoRepository
import com.anupkumar.instagramclonesample.data.repository.PostRepository
import com.anupkumar.instagramclonesample.data.repository.UserRepository
import com.anupkumar.instagramclonesample.ui.base.BaseActivity
import com.anupkumar.instagramclonesample.ui.login.LoginViewModel
import com.anupkumar.instagramclonesample.ui.main.MainSharedViewModel
import com.anupkumar.instagramclonesample.ui.main.MainViewModel
import com.anupkumar.instagramclonesample.ui.photo.PhotoViewModel
import com.anupkumar.instagramclonesample.ui.splash_screen.SplashActivity
import com.anupkumar.instagramclonesample.ui.splash_screen.SplashViewModel
import com.anupkumar.instagramclonesample.utils.ViewModelProviderFactory
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import java.io.File


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

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper): MainViewModel = ViewModelProviders.of(activity,ViewModelProviderFactory(MainViewModel::class){
        MainViewModel(schedulerProvider,compositeDisposable,networkHelper)
    }).get(MainViewModel::class.java)

    @Provides
    fun provideMainSharedViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): MainSharedViewModel = ViewModelProviders.of(
        activity!!, ViewModelProviderFactory(MainSharedViewModel::class) {
            MainSharedViewModel(schedulerProvider, compositeDisposable, networkHelper)
        }).get(MainSharedViewModel::class.java)

}