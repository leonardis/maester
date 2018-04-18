package example.maester.base

import android.app.Application
import example.maester.base.di.component.AppComponent
import example.maester.base.di.component.DaggerAppComponent
import example.maester.base.di.module.AppModule
import com.facebook.drawee.backends.pipeline.Fresco

class App: Application() {

    companion object{
       @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        Fresco.initialize(this)
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}