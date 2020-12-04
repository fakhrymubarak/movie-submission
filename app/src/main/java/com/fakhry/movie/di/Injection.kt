package com.fakhry.movie.di

import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): Repository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return Repository.getInstance(remoteDataSource)
    }
}