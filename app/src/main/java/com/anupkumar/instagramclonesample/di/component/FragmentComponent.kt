package com.anupkumar.instagramclonesample.di.component

import com.android.anupkumar.instagram.ui.profile.ProfileFragment
import com.anupkumar.instagramclonesample.di.FragmentScope
import com.anupkumar.instagramclonesample.di.module.FragmentModule
import com.anupkumar.instagramclonesample.ui.home.HomeFragment
import com.anupkumar.instagramclonesample.ui.photo.PhotoFragment
import com.anupkumar.instagramclonesample.ui.splash_screen.SplashActivity
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class],
modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: HomeFragment)

    fun inject(fragment: PhotoFragment)

    fun inject(fragment: ProfileFragment)
}