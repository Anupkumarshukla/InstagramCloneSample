package com.anupkumar.instagramclonesample.data.remote

import com.anupkumar.instagramclonesample.data.remote.request.DummyRequest
import com.anupkumar.instagramclonesample.data.remote.request.LoginRequest
import com.anupkumar.instagramclonesample.data.remote.request.PostCreationRequest
import com.anupkumar.instagramclonesample.data.remote.request.PostLikeModifyRequest
import com.anupkumar.instagramclonesample.data.remote.response.*
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface NetworkService {


    @POST(Endpoints.LOGIN)
    fun doLoginCall(
        @Body request: LoginRequest,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<LoginResponse>

    @Multipart
    @POST(Endpoints.UPLOAD_IMAGE)
    fun doImageUpload(
        @Part image: MultipartBody.Part,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<ImageResponse>

    @POST(Endpoints.CREATE_POST)
    fun doPostCreationCall(
        @Body request: PostCreationRequest,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<PostCreationResponse>





















}