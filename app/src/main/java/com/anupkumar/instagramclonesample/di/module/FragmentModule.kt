package com.anupkumar.instagramclonesample.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.anupkumar.instagramclonesample.data.model.Post
import com.anupkumar.instagramclonesample.data.repository.PhotoRepository
import com.anupkumar.instagramclonesample.data.repository.PostRepository
import com.anupkumar.instagramclonesample.data.repository.UserRepository
import com.anupkumar.instagramclonesample.di.TempDirectory
import com.anupkumar.instagramclonesample.ui.base.BaseFragment
import com.anupkumar.instagramclonesample.ui.home.HomeViewModel
import com.anupkumar.instagramclonesample.ui.photo.PhotoViewModel
import com.anupkumar.instagramclonesample.ui.splash_screen.SplashViewModel
import com.anupkumar.instagramclonesample.utils.ViewModelProviderFactory
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import com.mindorks.paracamera.Camera
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import java.io.File

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideCamera() = Camera.Builder()
        .resetToCorrectOrientation(true)
        .setTakePhotoRequestCode(1)
        .setDirectory("temp")
        .setName("camera_temp_img")
        .setImageFormat(Camera.IMAGE_JPEG)
        .setCompression(75)
        .setImageHeight(500)
        .build(fragment)

    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository,
        postRepository: PostRepository,
    ): HomeViewModel = ViewModelProviders.of(fragment, ViewModelProviderFactory(HomeViewModel::class){
        HomeViewModel(schedulerProvider,compositeDisposable,networkHelper,userRepository,postRepository,ArrayList(), PublishProcessor.create())
    }).get(HomeViewModel::class.java)

    @Provides
    fun providePhotoViewModel(schedulerProvider: SchedulerProvider,
     compositeDisposable: CompositeDisposable,
     networkHelper: NetworkHelper,
     userRepository: UserRepository,
     photoRepository: PhotoRepository,
     postRepository: PostRepository,
     @TempDirectory directory: File): PhotoViewModel = ViewModelProviders.of(fragment,ViewModelProviderFactory(PhotoViewModel::class){
        PhotoViewModel(schedulerProvider,compositeDisposable,networkHelper,userRepository,photoRepository,postRepository,directory)
    }).get(PhotoViewModel::class.java)

}