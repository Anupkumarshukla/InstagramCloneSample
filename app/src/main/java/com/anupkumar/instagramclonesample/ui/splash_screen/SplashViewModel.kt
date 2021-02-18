package com.anupkumar.instagramclonesample.ui.splash_screen

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.anupkumar.instagramclonesample.data.repository.UserRepository
import com.anupkumar.instagramclonesample.ui.base.BaseViewModel
import com.anupkumar.instagramclonesample.utils.common.Event
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(schedulerProvider: SchedulerProvider,
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