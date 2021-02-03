package com.anupkumar.instagramclonesample.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.anupkumar.instagramclonesample.BuildConfig
import com.anupkumar.instagramclonesample.MyApp
import com.anupkumar.instagramclonesample.data.local.db.DatabaseService
import com.anupkumar.instagramclonesample.data.remote.NetworkService
import com.anupkumar.instagramclonesample.data.remote.Networking
import com.anupkumar.instagramclonesample.di.ApplicationContext
import com.anupkumar.instagramclonesample.di.TempDirectory
import com.anupkumar.instagramclonesample.utils.common.FileUtils
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.RxSchedulerProvider
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application : MyApp) {

    @Singleton
    @Provides
    fun provideApplication(): Application = application


    @Singleton
    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @Provides
    @Singleton
    @TempDirectory
    fun provideTempDirectory() = FileUtils.getDirectory(application, "temp")


    @Provides
    fun provideCompositeDisposable() : CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider() : SchedulerProvider = RxSchedulerProvider()

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences =  application.getSharedPreferences("project-prefs", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideDatabaseService(): DatabaseService =  Room.databaseBuilder(
        application, DatabaseService::class.java,
        "project-db"
    ).build()

    @Singleton
    @Provides
    fun provideNetworkService() : NetworkService =  Networking.create(
        BuildConfig.API_KEY,
        BuildConfig.BASE_URL,
        application.cacheDir,
        10 * 1024 * 1024 // 10MB
    )

    @Singleton
    @Provides
    fun provideNetworkHelper() : NetworkHelper =  NetworkHelper(application)

}