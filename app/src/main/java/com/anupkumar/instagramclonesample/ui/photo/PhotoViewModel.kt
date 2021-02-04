package com.anupkumar.instagramclonesample.ui.photo

import androidx.lifecycle.MutableLiveData
import com.anupkumar.instagramclonesample.R
import com.anupkumar.instagramclonesample.data.model.Post
import com.anupkumar.instagramclonesample.data.model.User
import com.anupkumar.instagramclonesample.data.repository.PhotoRepository
import com.anupkumar.instagramclonesample.data.repository.PostRepository
import com.anupkumar.instagramclonesample.data.repository.UserRepository
import com.anupkumar.instagramclonesample.ui.base.BaseViewModel
import com.anupkumar.instagramclonesample.utils.common.Event
import com.anupkumar.instagramclonesample.utils.common.FileUtils
import com.anupkumar.instagramclonesample.utils.common.Resource
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import java.io.InputStream

class PhotoViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository,
    private val photoRepository: PhotoRepository,
    private val postRepository: PostRepository,
    private val directory: File) :
    BaseViewModel(schedulerProvider,compositeDisposable,networkHelper) {

    private val user: User = userRepository.getCurrentUser()!!
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val post: MutableLiveData<Event<Post>> = MutableLiveData()


    override fun onCreate() {}

    fun onGalleryImageSelected(inputStream: InputStream){
        loading.postValue(true)
        compositeDisposable.add(
            Single.fromCallable { FileUtils.saveInputStreamToFile(inputStream, directory, "gallery_img_temp", 500) }
                .subscribeOn(schedulerProvider.io())
                .subscribe({ file -> if(file != null) { FileUtils.getImageSize(file)?.let { uploadPhotoAndCreatePost(file,it) } }
                    else{
                        loading.postValue(false)
                        messageStringId.postValue(Resource.error(R.string.try_again))
                    } }, {
                        loading.postValue(false)
                        messageStringId.postValue(Resource.error(R.string.try_again))
                    }
                ))
    }

    fun onCameraImageTaken(cameraImageProcessor: () -> String){
        loading.postValue(true)
        compositeDisposable.add(
            Single.fromCallable { cameraImageProcessor() }
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        File(it).apply {
                            FileUtils.getImageSize(this)?.let {
                                    size -> uploadPhotoAndCreatePost(this, size)
                            }?: loading.postValue(false)
                        }
                    },
                    {
                        loading.postValue(false)
                        messageStringId.postValue(Resource.error(R.string.try_again))
                    }
                )
        )

    }

    private fun uploadPhotoAndCreatePost(imageFile: File, imageSize: Pair<Int, Int>){
        compositeDisposable.add(
            photoRepository.uploadPhoto(imageFile,user).flatMap {
                postRepository.createPost(it,imageSize.first,imageSize.second,user)
            }.subscribeOn(schedulerProvider.io())
             .subscribe(
                {
                    loading.postValue(false)
                    post.postValue(Event(it))
                },
                {
                    handleNetworkError(it)
                    loading.postValue(false)
                })
        )
    }


}