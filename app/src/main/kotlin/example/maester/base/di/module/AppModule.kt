package example.maester.base.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.google.gson.Gson;
import com.google.gson.GsonBuilder
import example.maester.utils.AppSchedulerProvider
import example.maester.utils.SharedPreferencesHelper
import io.reactivex.disposables.CompositeDisposable


@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun providesGson() = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    @Provides
    @Singleton
    fun providesApplication() = application

    @Provides
    @Singleton
    fun providesResources() = application.resources

    @Provides
    @Singleton
    fun providesSharedPref(gson: Gson) = SharedPreferencesHelper(application.getSharedPreferences("Sp", Context
            .MODE_PRIVATE), gson)

    @Provides
    @Singleton
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    @Singleton
    fun provideSchedulerProvider() = AppSchedulerProvider()

}