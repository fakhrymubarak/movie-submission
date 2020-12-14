package com.fakhry.movie.di

import android.content.Context
import com.fakhry.movie.data.Repository
import com.fakhry.movie.data.source.local.LocalDataSource
import com.fakhry.movie.data.source.local.room.MovieDatabase
import com.fakhry.movie.data.source.remote.RemoteDataSource
import com.fakhry.movie.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return Repository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}