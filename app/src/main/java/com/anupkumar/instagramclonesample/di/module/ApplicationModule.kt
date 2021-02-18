package com.anupkumar.instagramclonesample.di.module


import android.content.Context
import android.content.SharedPreferences
import com.anupkumar.instagramclonesample.BuildConfig
import com.anupkumar.instagramclonesample.data.model.Post
import com.anupkumar.instagramclonesample.data.remote.NetworkService
import com.anupkumar.instagramclonesample.data.remote.Networking
import com.anupkumar.instagramclonesample.di.TempDirectory
import com.anupkumar.instagramclonesample.utils.common.FileUtils
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.RxSchedulerProvider
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import com.mindorks.paracamera.Camera
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

  /*  @Singleton
    @Provides
    fun provideApplication(application: MyApp): Application = application*/

  /*  @Singleton
    @ApplicationContext
    @Provides
    fun provideContext(application: MyApp): Context = application*/

    @Provides
    @Singleton
    @TempDirectory
    fun provideDirectory(@ApplicationContext appContext: Context) = FileUtils.getDirectory(appContext, "temp")


    @Provides
    fun provideCompositeDisposable() : CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider() : SchedulerProvider = RxSchedulerProvider()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =  appContext.getSharedPreferences("project-prefs", Context.MODE_PRIVATE)

/*    @Singleton
    @Provides
    fun provideDatabaseService(application : MyApp): DatabaseService =  Room.databaseBuilder(
        application, DatabaseService::class.java,
        "project-db"
    ).build()*/

    @Singleton
    @Provides
    fun provideNetworkService(@ApplicationContext appContext: Context) : NetworkService =  Networking.create(
        BuildConfig.API_KEY,
        BuildConfig.BASE_URL,
        appContext.cacheDir,
        10 * 1024 * 1024 // 10MB
    )

    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext appContext: Context) : NetworkHelper =  NetworkHelper(appContext)

    @Singleton
    @Provides
    fun provideAllPostList() : ArrayList<Post> =  ArrayList<Post>()

    @Singleton
    @Provides
    fun providePublishProcessor() : PublishProcessor<Pair<String?, String?>> = PublishProcessor.create()

}