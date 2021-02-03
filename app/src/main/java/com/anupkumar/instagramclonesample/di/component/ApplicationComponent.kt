package com.anupkumar.instagramclonesample.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.anupkumar.instagramclonesample.MyApp
import com.anupkumar.instagramclonesample.data.local.db.DatabaseService
import com.anupkumar.instagramclonesample.data.remote.NetworkService
import com.anupkumar.instagramclonesample.data.repository.UserRepository
import com.anupkumar.instagramclonesample.di.ApplicationContext
import com.anupkumar.instagramclonesample.di.TempDirectory
import com.anupkumar.instagramclonesample.di.module.ApplicationModule
import com.anupkumar.instagramclonesample.utils.common.FileUtils
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import dagger.Component
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app : MyApp)

    fun getApplication() : Application

    @ApplicationContext
    fun getContext() : Context

    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getSharedPreferences(): SharedPreferences

    fun getNetworkHelper(): NetworkHelper

    fun getUserRepository(): UserRepository

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

    @TempDirectory
    fun getTempDirectory(): File
}