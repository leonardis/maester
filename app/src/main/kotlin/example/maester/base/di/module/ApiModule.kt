package example.maester.base.di.module

import dagger.Module
import dagger.Provides
import example.maester.api.Endpoints
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class ApiModule {
    @Provides
    @Singleton
    fun providesEndpoints(retrofit: Retrofit): Endpoints = retrofit.create(Endpoints::class.java)
}