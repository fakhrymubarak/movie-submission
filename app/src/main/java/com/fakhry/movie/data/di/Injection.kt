package com.fakhry.movie.data.di

import android.content.Context
import com.fakhry.movie.data.ApplicationRepository
import com.fakhry.movie.data.source.remote.RemoteDataSource
import com.fakhry.movie.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): ApplicationRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return ApplicationRepository.getInstance(remoteDataSource)
    }
}