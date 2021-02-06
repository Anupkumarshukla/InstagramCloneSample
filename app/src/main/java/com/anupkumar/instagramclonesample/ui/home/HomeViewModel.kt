package com.anupkumar.instagramclonesample.ui.home

import androidx.lifecycle.MutableLiveData
import com.anupkumar.instagramclonesample.data.model.Post
import com.anupkumar.instagramclonesample.data.model.User
import com.anupkumar.instagramclonesample.data.repository.PostRepository
import com.anupkumar.instagramclonesample.data.repository.UserRepository
import com.anupkumar.instagramclonesample.ui.base.BaseViewModel
import com.anupkumar.instagramclonesample.utils.common.Resource
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
    private val allPostList: ArrayList<Post>,
    private val paginator: PublishProcessor<Pair<String?, String?>>)
    :BaseViewModel(schedulerProvider,compositeDisposable,networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val posts: MutableLiveData<Resource<List<Post>>> = MutableLiveData()
    val refreshPosts: MutableLiveData<Resource<List<Post>>> = MutableLiveData()
    var firstId: String? = null
    var lastId: String? = null

    private val user: User = userRepository.getCurrentUser()!!

    init {
        loading.postValue(true)
        compositeDisposable.add(
            postRepository.fetchHomePostList(null, null, user)
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        allPostList.addAll(it)
                        loading.postValue(false)
                        posts.postValue(Resource.success(it))
                    },
                    {
                        loading.postValue(false)
                        handleNetworkError(it)
                    }
                )
        )
    }

    override fun onCreate() {}
}