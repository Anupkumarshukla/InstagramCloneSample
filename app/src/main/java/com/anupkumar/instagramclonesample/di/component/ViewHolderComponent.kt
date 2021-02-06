package com.anupkumar.instagramclonesample.di.component

import com.anupkumar.instagramclonesample.di.ViewModelScope
import com.anupkumar.instagramclonesample.di.module.ViewHolderModule
import com.anupkumar.instagramclonesample.ui.home.post.PostItemViewHolder
import dagger.Component


@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: PostItemViewHolder)
}