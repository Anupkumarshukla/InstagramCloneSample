package com.anupkumar.instagramclonesample.data.repository

import com.anupkumar.instagramclonesample.data.model.User
import com.anupkumar.instagramclonesample.data.remote.NetworkService
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class PhotoRepository @Inject constructor(private val networkService: NetworkService) {



}