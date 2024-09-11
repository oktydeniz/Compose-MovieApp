package com.writepulp.composemovieapp.data.di

import com.writepulp.composemovieapp.data.remote.MovieAPI
import com.writepulp.composemovieapp.data.repository.MovieRepositoryImp
import com.writepulp.composemovieapp.datautil.Constants
import com.writepulp.composemovieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMovieApi(): MovieAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieAPI): MovieRepository {
        return MovieRepositoryImp(api)
    }

}