package com.fakhry.movie.di

import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.remote.RemoteRepository

object Injection {
    fun provideRepository(): Repository {
        val remoteDataSource = RemoteRepository.getInstance()
        return Repository.getInstance(remoteDataSource)
    }
}