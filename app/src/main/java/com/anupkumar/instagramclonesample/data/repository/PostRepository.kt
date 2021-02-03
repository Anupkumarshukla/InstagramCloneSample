package com.anupkumar.instagramclonesample.data.repository

import com.anupkumar.instagramclonesample.data.local.db.DatabaseService
import com.anupkumar.instagramclonesample.data.model.Dummy
import com.anupkumar.instagramclonesample.data.model.Post
import com.anupkumar.instagramclonesample.data.model.User
import com.anupkumar.instagramclonesample.data.remote.NetworkService
import com.anupkumar.instagramclonesample.data.remote.request.DummyRequest
import io.reactivex.Single
import javax.inject.Inject

class PostRepository @Inject constructor(private val networkService: NetworkService,
    private val databaseService: DatabaseService) {






}