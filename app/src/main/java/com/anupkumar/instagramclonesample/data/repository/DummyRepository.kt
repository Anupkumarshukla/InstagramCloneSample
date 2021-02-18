package com.anupkumar.instagramclonesample.data.repository

import com.anupkumar.instagramclonesample.data.remote.NetworkService
import javax.inject.Inject

class DummyRepository @Inject constructor(private val networkService: NetworkService){}