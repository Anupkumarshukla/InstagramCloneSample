package com.anupkumar.instagramclonesample.ui.main

import androidx.lifecycle.MutableLiveData
import com.anupkumar.instagramclonesample.data.model.Post
import com.anupkumar.instagramclonesample.ui.base.BaseViewModel
import com.anupkumar.instagramclonesample.utils.common.Event
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainSharedViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {}

    val homeRedirection = MutableLiveData<Event<Boolean>>()

    val newPost: MutableLiveData<Event<Post>> = MutableLiveData()

    fun onHomeRedirect() {
        homeRedirection.postValue(Event(true))
    }
}