package com.anupkumar.instagramclonesample.ui.splash_screen

import androidx.lifecycle.MutableLiveData
import com.anupkumar.instagramclonesample.data.repository.UserRepository
import com.anupkumar.instagramclonesample.ui.base.BaseViewModel
import com.anupkumar.instagramclonesample.utils.common.Event
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(schedulerProvider: SchedulerProvider,
                      compositeDisposable: CompositeDisposable,
                      networkHelper: NetworkHelper,
                      val userRepository: UserRepository)
    : BaseViewModel(schedulerProvider,compositeDisposable,networkHelper){

    val launchMain:MutableLiveData<Event<Map<String,String>>> = MutableLiveData()
    val launchLogin:MutableLiveData<Event<Map<String,String>>> = MutableLiveData()




    override fun onCreate() {
        if (userRepository.getCurrentUser() != null)
            launchMain.postValue(Event(emptyMap()))
        else
            launchLogin.postValue(Event(emptyMap()))
    }


}