package com.anupkumar.instagramclonesample.di.module

import androidx.lifecycle.LifecycleRegistry
import com.anupkumar.instagramclonesample.di.ViewModelScope
import com.anupkumar.instagramclonesample.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder : BaseItemViewHolder<*,*>) {


    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)


}