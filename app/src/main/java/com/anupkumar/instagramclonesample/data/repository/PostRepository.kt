package com.anupkumar.instagramclonesample.data.repository

import com.anupkumar.instagramclonesample.data.local.db.DatabaseService
import com.anupkumar.instagramclonesample.data.model.Dummy
import com.anupkumar.instagramclonesample.data.model.Post
import com.anupkumar.instagramclonesample.data.model.User
import com.anupkumar.instagramclonesample.data.remote.NetworkService
import com.anupkumar.instagramclonesample.data.remote.request.DummyRequest
import com.anupkumar.instagramclonesample.data.remote.request.PostCreationRequest
import io.reactivex.Single
import javax.inject.Inject

class PostRepository @Inject constructor(private val networkService: NetworkService,
    private val databaseService: DatabaseService) {

    fun createPost(imgUrl: String, imgWidth: Int, imgHeight: Int, user: User) : Single<Post> = networkService.doPostCreationCall(
        PostCreationRequest(imgUrl,imgWidth,imgHeight),user.id,user.accessToken).map {
            Post(it.data.id, it.data.imageUrl, it.data.imageWidth, it.data.imageHeight,
            Post.User(user.id, user.name, user.profilePicUrl), mutableListOf(), it.data.createdAt)
        }
}