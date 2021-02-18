package com.anupkumar.instagramclonesample.di.module

import androidx.lifecycle.LifecycleRegistry
import com.anupkumar.instagramclonesample.di.ViewModelScope
import com.anupkumar.instagramclonesample.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ViewHolderModule{


    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(viewHolder : BaseItemViewHolder<*,*>): LifecycleRegistry = LifecycleRegistry(viewHolder)


}