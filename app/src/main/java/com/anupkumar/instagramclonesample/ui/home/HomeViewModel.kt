package com.anupkumar.instagramclonesample.ui.home

import com.anupkumar.instagramclonesample.ui.base.BaseViewModel
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper):BaseViewModel(schedulerProvider,compositeDisposable,networkHelper) {





    override fun onCreate() {

    }
}