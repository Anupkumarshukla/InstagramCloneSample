package com.anupkumar.instagramclonesample

import android.app.Application
import com.anupkumar.instagramclonesample.di.component.ApplicationComponent
import com.anupkumar.instagramclonesample.di.component.DaggerApplicationComponent
import com.anupkumar.instagramclonesample.di.module.ApplicationModule

class MyApp : Application(){

    lateinit var applicationComponent: ApplicationComponent


    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)


    }






}