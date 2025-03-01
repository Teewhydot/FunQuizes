package com.sirteefyapps.funquizes.di

import com.sirteefyapps.funquizes.data.data_source.FunQuizDataSource
import com.sirteefyapps.funquizes.domain.constants.Constants
import com.sirteefyapps.funquizes.domain.repository.FunQuizApi
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
    @Singleton
    @Provides
    fun provideFunQuizApi(): FunQuizApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FunQuizApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFunQuizDataSource(funQuizApi: FunQuizApi): FunQuizDataSource {
        return FunQuizDataSource(funQuizApi)
    }
}
