package com.anupkumar.instagramclonesample.ui.home.post

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.anupkumar.instagramclonesample.data.model.Post
import com.anupkumar.instagramclonesample.ui.base.BaseAdapter

class  PostsAdapter(
    parentLifecycle: Lifecycle,
    posts: ArrayList<Post>
) : BaseAdapter<Post, PostItemViewHolder>(parentLifecycle, posts) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostItemViewHolder(parent)
}