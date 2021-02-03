package com.anupkumar.instagramclonesample.di.component

import com.anupkumar.instagramclonesample.di.FragmentScope
import com.anupkumar.instagramclonesample.di.module.FragmentModule
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class],
modules = [FragmentModule::class])
interface FragmentComponent {




}