package example.maester.base.di.component

import android.app.Application
import android.content.res.Resources
import example.maester.base.di.module.ApiModule
import example.maester.base.di.module.AppModule
import example.maester.base.di.module.OkHttpModule
import example.maester.base.di.module.RetrofitModule
import example.maester.utils.SharedPreferencesHelper
import example.maester.utils.AppSchedulerProvider
import example.maester.api.Endpoints
import com.google.gson.Gson
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, RetrofitModule::class, ApiModule::class, OkHttpModule::class))
interface AppComponent {
    fun application(): Application
    fun gson(): Gson
    fun resources(): Resources
    fun retrofit():Retrofit
    fun endpoints():Endpoints
    fun cache(): Cache
    fun client(): OkHttpClient
    fun loggingInterceptor(): HttpLoggingInterceptor
    fun spHelper(): SharedPreferencesHelper
    fun compositeDisposable(): CompositeDisposable
    fun schedulerProvider(): AppSchedulerProvider
}