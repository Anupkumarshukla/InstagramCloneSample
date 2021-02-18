package com.anupkumar.instagramclonesample.ui.main

import androidx.lifecycle.MutableLiveData
import com.anupkumar.instagramclonesample.ui.base.BaseViewModel
import com.anupkumar.instagramclonesample.utils.common.Event
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper): BaseViewModel(schedulerProvider,compositeDisposable,networkHelper) {

    val profileNavigation : MutableLiveData<Event<Boolean>> = MutableLiveData()
    val homeNavigation : MutableLiveData<Event<Boolean>> = MutableLiveData()
    val photoNavigation : MutableLiveData<Event<Boolean>> = MutableLiveData()

    override fun onCreate() {
        homeNavigation.postValue(Event(true))
    }

    fun onProfileSelected(){
        profileNavigation.postValue(Event(true))
    }

    fun onHomeSelected(){
        homeNavigation.postValue(Event(true))
    }

    fun onPhotoSelected(){
        photoNavigation.postValue(Event(true))
    }
}